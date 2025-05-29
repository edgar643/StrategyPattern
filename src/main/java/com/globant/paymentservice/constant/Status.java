package com.globant.paymentservice.constant;

import lombok.Getter;

@Getter
public enum Status {
    SUCCESS("SUCCESS"),
    FAILED("FAILED");

    private final String status;

    Status(String status) {
        this.status = status;
    }
}
