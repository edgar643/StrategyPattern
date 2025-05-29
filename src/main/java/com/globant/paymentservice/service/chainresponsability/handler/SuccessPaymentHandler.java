package com.globant.paymentservice.service.chainresponsability.handler;

import com.globant.paymentservice.constant.Status;
import com.globant.paymentservice.model.PaymentResponse;

public class SuccessPaymentHandler extends PaymentResponseHandler {
    @Override
    public boolean canHandle(PaymentResponse response) {
        return Status.FAILED.getStatus().equalsIgnoreCase(response.getStatus());
    }

    @Override
    public void process(PaymentResponse response) {
        // Handle success logic
        System.out.println("Handling SUCCESS: " + response.getMessage());
    }
}
