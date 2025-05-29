package com.globant.paymentservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

/**
 * Represents the response of a payment operation.
 */
@Builder
@Data
@AllArgsConstructor
public class PaymentResponse {
    /**
     * The unique identifier for the transaction.
     */
    private String transactionId;

    /**
     * The status of the payment (e.g., SUCCESS, FAILED).
     */
    private String status;

    /**
     * A message providing additional details about the payment status.
     */
    private String message;
}
