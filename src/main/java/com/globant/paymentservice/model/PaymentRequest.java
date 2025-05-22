package com.globant.paymentservice.model;

import com.globant.paymentservice.constant.Payment_Method;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

/**
 * Represents a payment request containing details about the payment.
 */
@Data
public class PaymentRequest {

    /**
     * The amount to be paid.
     */
    @NotNull(message = "Invalid payment amount. Please provide a valid amount.")
    @Positive(message = "Invalid payment amount. Please provide a valid amount greater than zero.")
    private Double amount;

    /**
     * The currency in which the payment is made (e.g., USD, EUR).
     */
    @NotNull
    private String currency;

    /**
     * The method of payment (e.g., credit card, PayPal).
     */
    @NotNull(message = "Invalid payment method. Please provide a valid payment method.")
    private Payment_Method paymentMethod;

    /**
     * Additional details about the payment.
     */
    private PaymentDetails paymentDetails;
}
