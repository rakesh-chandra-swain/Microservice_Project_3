package com.bluepal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.bluepal.model.PaymentOrder;

public interface PaymentOrderRepository extends JpaRepository<PaymentOrder,Long> {

    //PaymentOrder findByPaymentLinkId(String paymentId);
    PaymentOrder findByPaymentLinkId(String orderId);
}
