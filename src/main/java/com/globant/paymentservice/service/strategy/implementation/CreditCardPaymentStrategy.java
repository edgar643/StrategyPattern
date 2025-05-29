package com.globant.paymentservice.service.strategy.implementation;

import com.globant.paymentservice.model.CreditCard;
import com.globant.paymentservice.model.PaymentRequest;

import com.globant.paymentservice.service.strategy.PaymentStrategy;

public class CreditCardPaymentStrategy implements PaymentStrategy {
    /**
     * Validates the payment request by checking the credit card details.
     *
     * @param paymentRequest The payment request containing credit card details.
     * @return true if the credit card details are valid, false otherwise.
     */
    @Override
    public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
        return validateCreditCard(paymentRequest);
    }

    /**
     * Validates the credit card details in the payment request.
     *
     * @param paymentRequest The payment request containing credit card details.
     * @return true if all credit card details (number, expiration date, CVV) are valid, false otherwise.
     */
    private boolean validateCreditCard(PaymentRequest paymentRequest) {
        CreditCard creditCard = paymentRequest.getPaymentDetails().getCreditCard();

        return validateCardNumber(creditCard.getCardNumber()) &&
                validateExpiryDate(creditCard.getExpirationDate()) &&
                validateCVV(creditCard.getCvv());
    }

    /**
     * Validates the credit card number.
     *
     * @param cardNumber The credit card number to validate.
     * @return true if the card number is a 16-digit numeric string, false otherwise.
     */
    private boolean validateCardNumber(String cardNumber) {
        return cardNumber != null && cardNumber.matches("\\d{16}");
    }

    /**
     * Validates the credit card expiration date.
     *
     * @param expiryDate The expiration date in the format MM/YY.
     * @return true if the expiration date matches the format MM/YY, false otherwise.
     */
    private boolean validateExpiryDate(String expiryDate) {
        return expiryDate != null && expiryDate.matches("(0[1-9]|1[0-2])/\\d{2}");
    }

    /**
     * Validates the credit card CVV.
     *
     * @param cvv The CVV (Card Verification Value) to validate.
     * @return true if the CVV is a 3-digit numeric string, false otherwise.
     */
    private boolean validateCVV(String cvv) {
        return cvv != null && cvv.matches("\\d{3}");
    }
}
