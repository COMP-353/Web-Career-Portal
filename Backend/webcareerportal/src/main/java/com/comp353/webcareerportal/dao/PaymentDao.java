package com.comp353.webcareerportal.dao;

import com.comp353.webcareerportal.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentDao extends JpaRepository<Payment, Long> {

}
