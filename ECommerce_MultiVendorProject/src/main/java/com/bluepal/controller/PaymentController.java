package com.bluepal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bluepal.domain.PaymentMethod;
import com.bluepal.model.Cart;
import com.bluepal.model.Order;
import com.bluepal.model.PaymentOrder;
import com.bluepal.model.Seller;
import com.bluepal.model.SellerReport;
import com.bluepal.model.User;
import com.bluepal.repository.CartItemRepository;
import com.bluepal.repository.CartRepository;
import com.bluepal.response.ApiResponse;
import com.bluepal.response.PaymentLinkResponse;
import com.bluepal.service.PaymentService;
import com.bluepal.service.SellerReportService;
import com.bluepal.service.SellerService;
import com.bluepal.service.TransactionService;
import com.bluepal.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final UserService userService;
    private final PaymentService paymentService;
    private final TransactionService transactionService;
    private final SellerReportService sellerReportService;
    private final SellerService sellerService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;


    @PostMapping("/api/payment/{paymentMethod}/order/{orderId}")
    public ResponseEntity<PaymentLinkResponse> paymentHandler(
            @PathVariable PaymentMethod paymentMethod,
            @PathVariable Long orderId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        PaymentLinkResponse paymentResponse;

        PaymentOrder order= paymentService.getPaymentOrderById(orderId);

//        if(paymentMethod.equals(PaymentMethod.RAZORPAY)){
//            paymentResponse=paymentService.createRazorpayPaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }
//        else{
//            paymentResponse=paymentService.createStripePaymentLink(user,
//                    order.getAmount(),
//                    order.getId());
//        }

        return new ResponseEntity<>(null, HttpStatus.CREATED);
    }


    @GetMapping("/api/payment/{paymentId}")
    public ResponseEntity<ApiResponse> paymentSuccessHandler(
            @PathVariable String paymentId,
            @RequestParam String paymentLinkId,
            @RequestHeader("Authorization") String jwt) throws Exception {

        User user = userService.findUserProfileByJwt(jwt);

        PaymentLinkResponse paymentResponse;

        PaymentOrder paymentOrder= paymentService
                .getPaymentOrderByPaymentId(paymentLinkId);

        boolean paymentSuccess = paymentService.ProceedPaymentOrder(
                paymentOrder,
                paymentId,
                paymentLinkId
        );
        if(paymentSuccess){
            for(Order order:paymentOrder.getOrders()){
                transactionService.createTransaction(order);
                Seller seller=sellerService.getSellerById(order.getSellerId());
                SellerReport report=sellerReportService.getSellerReport(seller);
                report.setTotalOrders(report.getTotalOrders()+1);
                report.setTotalEarnings(report.getTotalEarnings()+order.getTotalSellingPrice());
                report.setTotalSales(report.getTotalSales()+order.getOrderItems().size());
                sellerReportService.updateSellerReport(report);
            }
            Cart cart=cartRepository.findByUserId(user.getId());
            cart.setCouponPrice(0);
            cart.setCouponCode(null);
//        Set<CartItem> items=cart.getCartItems();
//        cartItemRepository.deleteAll(items);
//        cart.setCartItems(new HashSet<>());
            cartRepository.save(cart);

        }
      
        ApiResponse res = new ApiResponse();
        res.setMessage("Payment successful");
        res.setStatus(true);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
