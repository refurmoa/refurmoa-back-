package com.highfive.refurmoa.pay.repository;

import com.highfive.refurmoa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PayRepository extends JpaRepository<Payment, String> {

}
