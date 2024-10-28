package com.rtarcisio.paymentms.repositories;

import com.rtarcisio.paymentms.domains.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
}
