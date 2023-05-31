package com.highfive.refurmoa.user.service;

import java.util.List;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;

public interface CouponService {
	
	public List<Coupon> listCoupon(Member memberId);	//	쿠폰 조회
	public Coupon insertCoupon(Coupon coupon);	//	쿠폰 등록

}
