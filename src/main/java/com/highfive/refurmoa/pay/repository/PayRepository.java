package com.highfive.refurmoa.pay.repository;

import com.highfive.refurmoa.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PayRepository extends JpaRepository<Payment, String> {
	
}
