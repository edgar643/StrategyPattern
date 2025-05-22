package com.globant.paymentservice.repository;

import com.globant.paymentservice.model.PaymentDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for accessing PaymentDetails entities from the database.
 */
@Repository
public interface PaymentDetailsRepository extends JpaRepository<PaymentDetails, String> {
}
