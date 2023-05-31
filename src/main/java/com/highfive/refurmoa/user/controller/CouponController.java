package com.highfive.refurmoa.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.service.CouponServiceImpl;

@RestController
public class CouponController {
	
	public CouponController(CouponServiceImpl couponServiceImpl) {
		super();
		this.couponServiceImpl = couponServiceImpl;
	}
	
	private final CouponServiceImpl couponServiceImpl;
	
 	//	쿠폰 조회
 	@RequestMapping("/user/coupon")
 	public List<Coupon> listCoupon(@RequestBody Member memberId){
 		System.out.println(memberId);
 		return (List<Coupon>)couponServiceImpl.listCoupon(memberId);
 	}
 	
 	//	쿠폰 등록
	@RequestMapping("/admin/user/detail/coupon/insert")
	public Coupon insertCoupon(@RequestBody Coupon coupon) {
		return couponServiceImpl.insertCoupon(coupon);
	}

}
