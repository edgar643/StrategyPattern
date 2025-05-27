package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;

public class SuccessPaymentHandler extends PaymentResponseHandler {
    @Override
    public boolean canHandle(PaymentResponse response) {
        return "SUCCESS".equalsIgnoreCase(response.getStatus());
    }

    @Override
    public void process(PaymentResponse response) {
        // Handle success logic
        System.out.println("Handling SUCCESS: " + response.getMessage());
    }
}
