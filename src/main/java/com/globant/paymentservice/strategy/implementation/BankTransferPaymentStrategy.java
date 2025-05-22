package com.globant.paymentservice.strategy.implementation;

import com.globant.paymentservice.model.PaymentRequest;
import com.globant.paymentservice.strategy.PaymentStrategy;

public class BankTransferPaymentStrategy implements PaymentStrategy {

    @Override
    public boolean validatePaymentRequest(PaymentRequest paymentRequest) {
       return validateBankTransfer(paymentRequest);
    }

    /**
     * Validates the bank transfer details in the payment request.
     *
     * @param paymentRequest The payment request containing bank transfer details.
     * @return true if the bank account number is valid (not null, contains only digits,
     * is 10-12 digits long, and does not start with zero), false otherwise.
     */
    private boolean validateBankTransfer(PaymentRequest paymentRequest) {
        String bankAccountNumber = paymentRequest.getPaymentDetails().getBankAccountNumber();

        // Check if the bank account number is null
        if (bankAccountNumber == null) return false;

        // Validate the bank account number format
        return bankAccountNumber.matches("^[1-9]\\d{9,11}$");
    }
}
