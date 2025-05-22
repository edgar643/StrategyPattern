package com.globant.paymentservice.controller;

import com.globant.paymentservice.model.PaymentRequest;
import com.globant.paymentservice.model.PaymentResponse;
import com.globant.paymentservice.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payment")
public class PaymentController {
    private PaymentService paymentService;
@Autowired
        /**
         * Constructor for PaymentController.
         *
         * @param paymentService The PaymentService instance to handle payment processing logic.
         */
        public PaymentController(PaymentService paymentService) {
            this.paymentService = paymentService;
        }

        /**
         * Endpoint to process a payment.
         *
         * @param payment The PaymentRequest object containing payment details.
         *                It is validated using Jakarta Bean Validation annotations.
         * @return A PaymentResponse object containing the result of the payment processing.
         */
        @PostMapping("/pay")
        public PaymentResponse createPayment(@RequestBody @Valid PaymentRequest payment) {
            return paymentService.processPayment(payment);
        }
    }
