package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;

public class DefaultPaymentHandler extends PaymentResponseHandler {
    @Override
   public boolean canHandle(PaymentResponse response) {

        return true; // Handles any status not handled before
    }

    @Override
    public void process(PaymentResponse response) {
        System.out.println("Unhandled status: " + response.getStatus());
    }
}
