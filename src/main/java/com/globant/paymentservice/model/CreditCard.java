package com.globant.paymentservice.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
public class CreditCard {
    /**
     * The credit card number used for the payment.
     */
    @Id
    @NotNull
    private String cardNumber;

    /**
     * The expiration date of the credit card in MM/YY format.
     */
    @NotNull
    private String expirationDate;

    /**
     * The CVV (Card Verification Value) code of the credit card.
     */
    @NotNull
    private String cvv;
}
