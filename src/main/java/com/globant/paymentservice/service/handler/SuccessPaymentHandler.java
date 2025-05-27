package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class SuccessPaymentHandler extends PaymentResponseHandler {
    @Override
    protected boolean canHandle(PaymentResponse response) {
        return "SUCCESS".equalsIgnoreCase(response.getStatus());
    }

    @Override
    protected void process(PaymentResponse response) {
        // Handle success logic
        System.out.println("Handling SUCCESS: " + response.getMessage());
    }
}
