package com.globant.paymentservice.service.strategy.implementation;

import com.globant.paymentservice.model.PaymentRequest;
import com.globant.paymentservice.service.strategy.PaymentStrategy;

public class PayPalPaymentStrategy implements PaymentStrategy {
    @Override
    public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
        return validatePaypalEmail(paymentRequest);
    }

    private boolean validatePaypalEmail(PaymentRequest paymentRequest) {
        String email = paymentRequest.getPaymentDetails().getPaypalEmail();
        // For example, check if the email is not null and follows a valid format
        String EMAIL_PATTERN = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";
        return email != null && email.matches(EMAIL_PATTERN);
    }
}
