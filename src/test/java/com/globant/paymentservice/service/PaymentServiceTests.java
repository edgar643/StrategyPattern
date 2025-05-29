package com.globant.paymentservice.service;

import com.globant.paymentservice.constant.Payment_Method;
import com.globant.paymentservice.constant.Status;
import com.globant.paymentservice.exception.BusinessException;
import com.globant.paymentservice.model.CreditCard;
import com.globant.paymentservice.model.PaymentDetails;
import com.globant.paymentservice.model.PaymentRequest;
import com.globant.paymentservice.model.PaymentResponse;
import com.globant.paymentservice.repository.TransactionRepository;
import com.globant.paymentservice.service.chainresponsability.PaymentResponseProcessor;
import com.globant.paymentservice.service.strategy.PaymentContext;
import com.globant.paymentservice.exception.ErrorCode;
import com.globant.paymentservice.service.strategy.implementation.CreditCardPaymentStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTests {

    private PaymentContext paymentContext;

    @Mock
    private TransactionRepository transactionRepository;

    @Mock
    private PaymentDetailsInitializer paymentDetailsInitializer;

    @Mock
    private PaymentResponseProcessor paymentResponseProcessor;

    @Mock
    private CreditCardPaymentStrategy paymentStrategy;

    @InjectMocks
    private PaymentService paymentService;

    private PaymentRequest paymentRequest;
    private PaymentResponse paymentResponsePaypal;
    private PaymentDetails paymentDetails;
    private PaymentResponse paymentResponseCreditCard;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        paymentContext = new PaymentContext();
        paymentRequest = new PaymentRequest();
        paymentRequest.setPaymentMethod(Payment_Method.CREDIT_CARD);
        paymentDetails = new PaymentDetails();
        paymentResponsePaypal = new PaymentResponse("0de5788d-7d33-4a75-ad84-cdf35aa97087",
                Status.SUCCESS.getStatus(), "Payment PAYPAL Success");
        paymentResponseCreditCard = new PaymentResponse("0de5788d-7d33-4a75-ad84-cdf35aa97087",
                Status.SUCCESS.getStatus(), "Payment Credit_Card Success");
        paymentService = new PaymentService(paymentContext, paymentDetailsInitializer, transactionRepository, paymentResponseProcessor);
    }

    @Test
    void processesPaymentSuccessfullyWithValidStrategy() {
        //GIVEN
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1234567890123456");
        creditCard.setExpirationDate("12/25");
        creditCard.setCvv("123");

        paymentDetails.setCreditCard(creditCard);
        paymentRequest.setPaymentDetails(paymentDetails);
        paymentRequest.setAmount(50.0);
        paymentRequest.setCurrency("USD");

        HashMap<Payment_Method, PaymentDetails> paymentDetailsMap = new HashMap<>();
        paymentDetailsMap.put(Payment_Method.CREDIT_CARD, paymentDetails);
        when(paymentDetailsInitializer.getPaymentDetailsMap()).thenReturn(paymentDetailsMap);
        when(transactionRepository.save(any())).thenReturn(null);

        //WHEN
        final PaymentResponse response = paymentService.processPayment(paymentRequest);

        //THEN
        assertNotNull(response);
        assertEquals(Status.SUCCESS.getStatus(), response.getStatus());
    }

    @Test
    void handlesTransactionSaveFailureGracefully() {
        //GIVEN
        CreditCard creditCard = new CreditCard();
        creditCard.setCardNumber("1234567890123456");
        creditCard.setExpirationDate("12/25");
        creditCard.setCvv("123");
        paymentDetails.setCreditCard(creditCard);
        paymentRequest.setPaymentDetails(paymentDetails);
        paymentRequest.setAmount(50.0);
        paymentRequest.setCurrency("USD");
        HashMap<Payment_Method, PaymentDetails> paymentDetailsMap = new HashMap<>();
        paymentDetailsMap.put(Payment_Method.CREDIT_CARD, paymentDetails);
        when(paymentDetailsInitializer.getPaymentDetailsMap()).thenReturn(paymentDetailsMap);
        doThrow(new RuntimeException("Database error")).when(transactionRepository).save(any());

        //WHEN
        BusinessException exception = assertThrows(BusinessException.class, () -> paymentService.processPayment(paymentRequest));

        //THEN
        assertEquals(ErrorCode.ERR_TRANSACTION.getMessage(), exception.getMessage());
        verify(paymentResponseProcessor)
                .processPaymentResponse(any(PaymentResponse.class));
    }
}
