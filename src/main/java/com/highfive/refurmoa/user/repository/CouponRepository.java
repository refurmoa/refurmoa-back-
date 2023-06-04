package com.highfive.refurmoa.user.repository;

import java.util.Date;
import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByMemberId(Member memberId);	//	쿠폰 조회

    @Transactional
    @Modifying
    @Query("UPDATE Coupon c SET c.useCheck = true, c.useDate = :today WHERE c.couponNum = :couponNum")
    void useCoupon(Integer couponNum, Date today); // 쿠폰 사용

}
