package com.highfive.refurmoa.user.DTO.reponse;

import java.util.List;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Mile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MembershipDTO {
	
	private memberGradeDTO membergrade;
	private mileDTO mile;
	private List<couponDTO> coupon;
}
