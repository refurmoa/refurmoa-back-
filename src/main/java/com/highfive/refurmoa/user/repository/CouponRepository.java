package com.highfive.refurmoa.user.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;

public interface CouponRepository extends JpaRepository<Coupon, Integer> {

	List<Coupon> findByMemberId(Member memberId);	//	쿠폰 조회
	@Query(value="select * from coupon  where member_id= :memberId and use_check=0 and valid_date >:date order by coupon_num DESC ",nativeQuery=true)
	public List<Coupon> memCoupon(@Param("memberId") String memberId,@Param("date") Date date);
}
