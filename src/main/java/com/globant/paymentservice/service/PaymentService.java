package com.globant.paymentservice.service;

    import com.globant.paymentservice.constant.Payment_Method;
    import com.globant.paymentservice.exception.BusinessException;
    import com.globant.paymentservice.exception.ErrorCode;
    import com.globant.paymentservice.model.PaymentDetails;
    import com.globant.paymentservice.model.PaymentRequest;
    import com.globant.paymentservice.model.PaymentResponse;
    import com.globant.paymentservice.model.Transaction;
    import com.globant.paymentservice.repository.TransactionRepository;
    import com.globant.paymentservice.service.handler.DefaultPaymentHandler;
    import com.globant.paymentservice.service.handler.PaymentResponseHandler;
    import com.globant.paymentservice.strategy.PaymentContext;
    import com.globant.paymentservice.service.handler.*;
    import com.globant.paymentservice.strategy.PaymentStrategy;
    import jakarta.transaction.Transactional;
    import lombok.extern.slf4j.Slf4j;
    import org.springframework.stereotype.Service;

    import java.util.List;

/**
     * Service class responsible for handling payment processing logic.
     */
    @Slf4j
    @Service
    public class PaymentService {
    final private PaymentContext paymentContext;
    final private PaymentDetailsInitializer paymentDetailsInitializer;
    final private TransactionRepository transactionRepository;
    final private SuccessPaymentHandler successHandler;
    final private FailedPaymentHandler failedHandler;
    final private DefaultPaymentHandler defaultHandler;
    private PaymentStrategy strategy;

    /**
         * Constructor to initialize the PaymentService with required dependencies.
         *
         * @param paymentContext              The payment context containing strategies.
         * @param paymentDetailsInitializer   The initializer for payment details.
         * @param transactionRepository       The repository for transaction persistence.
         */
        public PaymentService(PaymentContext paymentContext,
                              PaymentDetailsInitializer paymentDetailsInitializer,
                              TransactionRepository transactionRepository,
                              SuccessPaymentHandler successHandler,
                              FailedPaymentHandler failedHandler,
                              DefaultPaymentHandler defaultHandler) {
            this.paymentContext = paymentContext;
            this.paymentDetailsInitializer = paymentDetailsInitializer;
            this.transactionRepository = transactionRepository;
            this.successHandler = successHandler;
            this.failedHandler = failedHandler;
            this.defaultHandler = defaultHandler;
            this.successHandler.setNextHandler(failedHandler);
            this.failedHandler.setNextHandler(defaultHandler);
        }

        /**
         * Processes a payment request and returns the payment response.
         *
         * @param paymentRequest The payment request containing input data.
         * @return The payment response containing the result of the transaction.
         * @throws BusinessException If the transaction fails or the payment method is invalid.
         */
        @Transactional
        public PaymentResponse processPayment(final PaymentRequest paymentRequest) {
            setStrategy(paymentRequest.getPaymentMethod());
            final var paymentDetails = getPaymentDetails(paymentRequest);
            paymentRequest.setPaymentDetails(paymentDetails);
            final var paymentResponse = strategy.processPayment(paymentRequest);
            log.info("Payment response: {}", paymentResponse);
            final var transactionSaved = saveTransaction(paymentRequest, paymentResponse);
            // To handle a response:
            successHandler.handle(paymentResponse);

            if (!transactionSaved) {
                log.error("Transaction saved failed");
                throw new BusinessException(ErrorCode.ERR_TRANSACTION.getMessage(), ErrorCode.ERR_TRANSACTION.getCode());
            }
            return paymentResponse;
        }

        /**
         * Saves a transaction to the database.
         *
         * @param paymentRequest  The payment request containing input data.
         * @param paymentResponse The payment response containing the result of the transaction.
         * @return True if the transaction was saved successfully, false otherwise.
         */
        @Transactional
        boolean saveTransaction(PaymentRequest paymentRequest, PaymentResponse paymentResponse) {
            Transaction transaction = new Transaction(paymentRequest, paymentResponse);
            try {
                transactionRepository.save(transaction);
                return true;
            } catch (Exception exception) {
                log.error(exception.getMessage());
                return false;
            }
        }

        /**
         * Retrieves payment details based on the payment method in the request.
         *
         * @param paymentRequest The payment request containing the payment method.
         * @return The payment details associated with the payment method.
         */
        private PaymentDetails getPaymentDetails(PaymentRequest paymentRequest) {
            Payment_Method paymentMethod = paymentRequest.getPaymentMethod();
            return paymentDetailsInitializer.getPaymentDetailsMap().get(paymentMethod);
        }

        /**
         * Sets the payment strategy based on the provided payment method.
         *
         * @param paymentMethod The payment method to determine the strategy.
         * @throws BusinessException If no strategy is found for the payment method.
         */
        private void setStrategy(Payment_Method paymentMethod) {
            strategy = paymentContext.getStrategiesMap().get(paymentMethod);
            if (strategy == null) {
                log.error(ErrorCode.ERR_PAYMENT_METHOD.getMessage(), ErrorCode.ERR_PAYMENT_METHOD.getCode(), paymentMethod);
                throw new BusinessException(ErrorCode.ERR_PAYMENT_METHOD.getMessage(), ErrorCode.ERR_PAYMENT_METHOD.getCode());
            }
        }
    }
