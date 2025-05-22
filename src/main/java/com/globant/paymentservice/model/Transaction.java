package com.globant.paymentservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class representing a transaction in the system.
 * This class is mapped to the "transactions" table in the database.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "transactions") // Specifies the table name in the database
public class Transaction {

    /**
     * Unique identifier for the transaction.
     */
    @Id
    private String transactionId;

    /**
     * Status of the transaction (e.g., SUCCESS, FAILED).
     */
    private String status;

    /**
     * Message providing additional details about the transaction.
     */
    private String message;

    /**
     * Payment method used for the transaction (e.g., CREDIT_CARD, PAYPAL).
     */
    private String paymentMethod;

    /**
     * Details of the payment associated with the transaction.
     */
    private String paymentDetails;

    /**
     * Original payment request data serialized as a string.
     */
    private String paymentRequest;

    /**
     * Constructs a Transaction object using a payment request and response.
     *
     * @param paymentRequest  The payment request containing input data for the transaction.
     * @param paymentResponse The payment response containing the result of the transaction.
     */
    public Transaction(PaymentRequest paymentRequest, PaymentResponse paymentResponse) {
        this.transactionId = paymentResponse.getTransactionId();
        this.status = paymentResponse.getStatus();
        this.message = paymentResponse.getMessage();
        this.paymentMethod = paymentRequest.getPaymentMethod().toString();
        this.paymentDetails = paymentRequest.getPaymentDetails().toString();
        this.paymentRequest = paymentRequest.toString();
    }
}
