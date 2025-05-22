package com.bluepal.dto;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.bluepal.domain.OrderStatus;
import com.bluepal.domain.PaymentStatus;
import com.bluepal.model.Address;
import com.bluepal.model.PaymentDetails;

@Data
public class OrderDto {

    private Long id;

    private String orderId;

    private UserDto user;

    private Long sellerId;

    private List<OrderItemDto> orderItems = new ArrayList<>();

    private Address shippingAddress;

    private PaymentDetails paymentDetails=new PaymentDetails();

    private double totalMrpPrice;

    private Integer totalSellingPrice;

    private Integer discount;

    private OrderStatus orderStatus;

    private int totalItem;

    private PaymentStatus paymentStatus=PaymentStatus.PENDING;

    private LocalDateTime orderDate = LocalDateTime.now();
    private LocalDateTime deliverDate = orderDate.plusDays(7);

}
