package com.globant.paymentservice.exception;

/**
 * Enum representing all kinds of business error codes and their messages.
 */
public  enum ErrorCode {
    ERR_PAYMENT_METHOD("ERR_PAYMENT_METHOD", "Payment method not supported."),
    ERR_PAYMENT_NOT_FOUND("ERR_PAYMENT_NOT_FOUND", "Payment not found."),
    ERR_TRANSACTION_FAILED("ERR_TRANSACTION_FAILED", "Transaction failed."),
    ERR_TRANSACTION("ERR_SAVING_RESULT", "Transaction could not be saved.");


    private final String code;
    private final String message;

    ErrorCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
