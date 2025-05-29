package com.globant.paymentservice.service;

import com.globant.paymentservice.constant.Payment_Method;
import com.globant.paymentservice.model.PaymentDetails;
import com.globant.paymentservice.repository.PaymentDetailsRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;

/**
 * Bean to retrieve PaymentDetails information when the application starts.
 */
@Component
@Getter
@Slf4j
/**
 * Initializes payment details from the database and stores them in a map for quick access.
 */
public class PaymentDetailsInitializer {

    /**
     * Repository to access payment details from the database.
     */
    private final PaymentDetailsRepository paymentDetailsRepository;

    /**
     * A map to store payment details categorized by payment methods.
     */
    private final HashMap<Payment_Method, PaymentDetails> paymentDetailsMap = new HashMap<>();

    /**
     * Constructor to inject the PaymentDetailsRepository dependency.
     *
     * @param paymentDetailsRepository The repository to fetch payment details from the database.
     */
    @Autowired
    public PaymentDetailsInitializer(PaymentDetailsRepository paymentDetailsRepository) {
        this.paymentDetailsRepository = paymentDetailsRepository;
    }

    /**
     * Initializes the payment details map after the bean is constructed.
     * Retrieves all payment details from the database and maps them to their respective payment methods.
     * Logs an error if there are fewer than three payment details in the database.
     */
    @PostConstruct
    public void init() {
        List<PaymentDetails> paymentDetailsList = paymentDetailsRepository.findAll();
        if (paymentDetailsList.size() >= 3) {
            paymentDetailsMap.put(Payment_Method.CREDIT_CARD, paymentDetailsList.get(0));
            paymentDetailsMap.put(Payment_Method.BANK_TRANSFER, paymentDetailsList.get(1));
            paymentDetailsMap.put(Payment_Method.PAYPAL, paymentDetailsList.get(2));
        } else {
            log.error("Not enough payment details found in the database. Please execute the SQL script to insert the payment details.");
        }
    }
}
