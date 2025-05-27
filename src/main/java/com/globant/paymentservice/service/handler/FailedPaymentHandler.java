package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;

public class FailedPaymentHandler extends PaymentResponseHandler {
    @Override
   public boolean canHandle(PaymentResponse response) {
        return "FAILED".equalsIgnoreCase(response.getStatus());
    }

    @Override
    public void process(PaymentResponse response) {
        // Handle failure logic
        System.out.println("Handling FAILED: " + response.getMessage());
    }
}
