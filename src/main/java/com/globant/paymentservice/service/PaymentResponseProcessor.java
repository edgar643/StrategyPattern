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

    /**
     * Processes a payment response by finding the first handler that can handle the response
     * and delegating the processing to it.
     * <p>
     * The method uses a stream to iterate over the list of payment response handlers,
     * filters handlers that can handle the given response, and processes the response
     * using the first matching handler.
     *
     * @param response The payment response to be processed.
     */
    public void processPaymentResponse(PaymentResponse response) {
        paymentResponseHandlerList.stream()
                .filter(paymentResponseHandler -> paymentResponseHandler.canHandle(response))
                .findFirst()
                .ifPresent(paymentResponseHandler -> paymentResponseHandler.process(response));
    }
}
