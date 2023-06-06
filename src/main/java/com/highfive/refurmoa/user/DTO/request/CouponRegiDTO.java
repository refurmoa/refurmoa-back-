package com.highfive.refurmoa.user.DTO.request;

import java.util.Date;

import com.highfive.refurmoa.entity.Member;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CouponRegiDTO {
	
	 private String memberId;
	 private String couponName;
	 private int salePrice;
	 private Date issueDate;
	 private Date validDate;
}
