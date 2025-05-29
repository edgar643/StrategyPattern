package com.globant.paymentservice.service.strategy;

import com.globant.paymentservice.constant.Payment_Method;
import com.globant.paymentservice.service.strategy.implementation.BankTransferPaymentStrategy;
import com.globant.paymentservice.service.strategy.implementation.CreditCardPaymentStrategy;
import com.globant.paymentservice.service.strategy.implementation.PayPalPaymentStrategy;
import lombok.Getter;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Component
@Getter
public class PaymentContext {
private Map<Payment_Method, PaymentStrategy> strategiesMap;

    public PaymentContext() {
        Map<Payment_Method, PaymentStrategy> tempMap = new HashMap<>();
        tempMap.put(Payment_Method.CREDIT_CARD, new CreditCardPaymentStrategy());
        tempMap.put(Payment_Method.PAYPAL, new PayPalPaymentStrategy());
        tempMap.put(Payment_Method.BANK_TRANSFER, new BankTransferPaymentStrategy());
        // Make the strategies map unmodifiable to prevent further changes after initialization
        this.strategiesMap = Collections.unmodifiableMap(tempMap);
    }
}
