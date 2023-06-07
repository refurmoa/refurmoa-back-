package com.highfive.refurmoa.user.service;

import java.util.List;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.DTO.request.CouponRegiDTO;

public interface CouponService {
	
	public List<Coupon> listCoupon(Member memberId);	//	쿠폰 조회
	public int insertCoupon(CouponRegiDTO coupon);	//	쿠폰 등록

}
