package com.globant.paymentservice.service.handler;

import com.globant.paymentservice.model.PaymentResponse;
import lombok.Setter;
import org.springframework.stereotype.Component;


@Component
@Setter
public abstract class PaymentResponseHandler {
    protected PaymentResponseHandler nextHandler;
    public abstract boolean canHandle(PaymentResponse response);
    public abstract void process(PaymentResponse response);
}
