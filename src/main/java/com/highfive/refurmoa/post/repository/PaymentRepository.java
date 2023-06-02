package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    Page<Payment> findByMemberId(Member member, Pageable pageable);
}
