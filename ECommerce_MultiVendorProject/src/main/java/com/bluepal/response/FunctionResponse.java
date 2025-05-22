package com.bluepal.response;

import com.bluepal.dto.OrderHistory;
import com.bluepal.model.Cart;
import com.bluepal.model.Product;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FunctionResponse {
    private String functionName;
    private Cart userCart;
    private OrderHistory orderHistory;
    private Product product;
}
