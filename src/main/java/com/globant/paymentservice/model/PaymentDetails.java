package com.globant.paymentservice.model;


import jakarta.persistence.*;
import lombok.Data;

/**
 * Represents the payment details associated with a payment method.
 * This class includes information for various payment types such as
 * credit card, PayPal, and bank account.
 */
@Data
@Entity
public class PaymentDetails {
    @Id
    private String id;
    /**
     * The credit card details used for the payment.
     */
    @OneToOne
    @JoinColumn(name = "card_number")
    private CreditCard creditCard;

    /**
     * The email address associated with the PayPal account.
     */

    private String paypalEmail;

    /**
     * The bank account number used for the payment.
     */
    private String bankAccountNumber;

}
