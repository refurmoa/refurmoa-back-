package com.highfive.refurmoa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByMemberId(Member memberId);	//	쿠폰 조회
	@Query(value="select * from coupon  where member_id= :memberId order by coupon_num DESC limit 3 ",nativeQuery=true)
	public List<Coupon> memCoupon(@Param("memberId") String memberId);
}
