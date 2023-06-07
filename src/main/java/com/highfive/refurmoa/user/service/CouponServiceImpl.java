package com.highfive.refurmoa.user.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.pay.repository.PaymentRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.UserlikeRepository;
import com.highfive.refurmoa.user.DTO.request.CouponRegiDTO;
import com.highfive.refurmoa.user.repository.CouponRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import com.highfive.refurmoa.user.repository.MileRepository;

import lombok.RequiredArgsConstructor;

@Service
public class CouponServiceImpl implements CouponService {
	
	private final CouponRepository repository;
	private MemberRepository memrepository;
	 public CouponServiceImpl(MemberRepository memrepository,CouponRepository repository) {
	        this.repository = repository;
	        this.memrepository=memrepository;
	    }
	//	쿠폰 조회
	@Override
	public List<Coupon> listCoupon(Member memberId) {
		return (List<Coupon>)repository.findByMemberId(memberId);
	}

	//	쿠폰 등록
	@Override
	public int insertCoupon(CouponRegiDTO coupon) {
		Member mem=memrepository.findByMemberId(coupon.getMemberId());
		Coupon tmp=new Coupon(mem,coupon);
		repository.save(tmp);
		return 1;
	}

}
