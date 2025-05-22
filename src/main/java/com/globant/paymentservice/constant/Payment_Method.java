package com.globant.paymentservice.constant;

import lombok.Getter;

/**
 * Enum representing different payment methods.
 * Each payment method is associated with a string value.
 */
@Getter
public enum Payment_Method {
    /**
     * Payment method for credit card transactions.
     */
    CREDIT_CARD("CREDIT_CARD"),

    /**
     * Payment method for bank transfer transactions.
     */
    BANK_TRANSFER("BANK_TRANSFER"),

    /**
     * Payment method for PayPal transactions.
     */
    PAYPAL("PAYPAL");

    // The string representation of the payment method.
    private final String method;

    /**
     * Constructor to initialize the payment method with its string value.
     *
     * @param method The string representation of the payment method.
     */
    Payment_Method(String method) {
        this.method = method;
    }
}
