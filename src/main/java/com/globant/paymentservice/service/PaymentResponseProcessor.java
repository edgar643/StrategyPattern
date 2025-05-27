package com.globant.paymentservice.service;

import com.globant.paymentservice.model.PaymentResponse;
import com.globant.paymentservice.service.handler.DefaultPaymentHandler;
import com.globant.paymentservice.service.handler.FailedPaymentHandler;
import com.globant.paymentservice.service.handler.PaymentResponseHandler;
import com.globant.paymentservice.service.handler.SuccessPaymentHandler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PaymentResponseProcessor {
    private final List<PaymentResponseHandler> paymentResponseHandlerList = new ArrayList<>();

    public PaymentResponseProcessor() {
        paymentResponseHandlerList.addAll(List.of(new SuccessPaymentHandler(), new FailedPaymentHandler(), new DefaultPaymentHandler()));
    }

    public void processPaymentResponse(PaymentResponse response) {
        paymentResponseHandlerList.stream().filter(paymentResponseHandler -> paymentResponseHandler.canHandle(response)).findFirst().ifPresent(paymentResponseHandler -> paymentResponseHandler.process(response));
    }
}
