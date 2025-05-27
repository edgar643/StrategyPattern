package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Setter
public abstract class PaymentResponseHandler {
    protected PaymentResponseHandler nextHandler;

    public void handle(PaymentResponse response) {
        if (canHandle(response)) {
            process(response);
        } else if (nextHandler != null) {
            nextHandler.handle(response);
        }
    }

    protected abstract boolean canHandle(PaymentResponse response);
    protected abstract void process(PaymentResponse response);
}
