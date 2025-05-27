package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class DefaultPaymentHandler extends PaymentResponseHandler {
    @Override
    protected boolean canHandle(PaymentResponse response) {

        return true; // Handles any status not handled before
    }

    @Override
    protected void process(PaymentResponse response) {
        System.out.println("Unhandled status: " + response.getStatus());
    }
}
