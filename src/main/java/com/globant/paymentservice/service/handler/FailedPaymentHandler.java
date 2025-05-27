package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;
import org.springframework.stereotype.Component;

@Component
public class FailedPaymentHandler extends PaymentResponseHandler {
    @Override
    protected boolean canHandle(PaymentResponse response) {
        return "FAILED".equalsIgnoreCase(response.getStatus());
    }

    @Override
    protected void process(PaymentResponse response) {
        // Handle failure logic
        System.out.println("Handling FAILED: " + response.getMessage());
    }
}
