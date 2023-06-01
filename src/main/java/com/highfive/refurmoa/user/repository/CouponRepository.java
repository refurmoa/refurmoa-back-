package com.highfive.refurmoa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByMemberId(Member memberId);	//	쿠폰 조회
}
