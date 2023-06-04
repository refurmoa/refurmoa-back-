package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // 결제 내역 조회
    Page<Payment> findByMemberMemberId(String memberId, Pageable pageable);

    // 결제 내역 기간 조회
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND :startDate < p.payDate " +
            "AND p.payDate < :endDate"
    )
    Page<Payment> findByMemberIdAndPeriod(String memberId, Date startDate, Date endDate, Pageable pageable);

    // 결제 내역 검색
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND p.product.prodName like %:search%"
    )
    Page<Payment> findByMemberIdAndSearch(String memberId, String search, Pageable pageable);
}
