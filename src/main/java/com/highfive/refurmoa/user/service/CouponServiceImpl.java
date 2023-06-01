package com.highfive.refurmoa.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.repository.CouponRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
	
	private final CouponRepository repository;

	//	쿠폰 조회
	@Override
	public List<Coupon> listCoupon(Member memberId) {
		return (List<Coupon>)repository.findByMemberId(memberId);
	}

	//	쿠폰 등록
	@Override
	public Coupon insertCoupon(Coupon coupon) {
		return repository.save(coupon);
	}

}
