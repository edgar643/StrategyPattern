package com.globant.paymentservice.service.chainresponsability.handler;

import com.globant.paymentservice.constant.Status;
import com.globant.paymentservice.model.PaymentResponse;

public class FailedPaymentHandler extends PaymentResponseHandler {
    @Override
   public boolean canHandle(PaymentResponse response) {
        return Status.FAILED.getStatus().equalsIgnoreCase(response.getStatus());
    }

    @Override
    public void process(PaymentResponse response) {
        // Handle failure logic
        System.out.println("Handling FAILED: " + response.getMessage());
    }
}
