package com.globant.paymentservice.strategy;

import com.globant.paymentservice.model.PaymentRequest;
import com.globant.paymentservice.model.PaymentResponse;

import java.util.UUID;

public interface PaymentStrategy {

    /**
     * Processes a payment based on the provided payment request.
     *
     * @param paymentRequest The payment request containing details about the payment.
     * @return A response indicating the result of the payment operation.
     */
    default PaymentResponse processPayment(PaymentRequest paymentRequest) {
        if (validatePaymentRequest(paymentRequest)) {
            return PaymentResponse.builder()
                                  .message("Payment " + paymentRequest.getPaymentMethod().name() + " Success")
                                  .status("SUCCESS")
                                  .transactionId(UUID.randomUUID().toString())
                                  .build();
        } else {
            return PaymentResponse.builder()
                                  .message("Payment " + paymentRequest.getPaymentMethod().name() + " Failed")
                                  .status("FAILED")
                                  .transactionId(UUID.randomUUID().toString())
                                  .build();
        }
    }

    /**
     * Validates the payment request to ensure all required fields are present and valid.
     *
     * @param paymentRequest The payment request to validate.
     * @return true if the payment request is valid, false otherwise.
     */
    boolean validatePaymentRequest(PaymentRequest paymentRequest);
}
