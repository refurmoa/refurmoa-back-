package com.highfive.refurmoa.pay.dto.response;

import java.util.Date;

import com.highfive.refurmoa.entity.Coupon;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class couponListDTO {
	private int coupon_num;
	private String coupon_name;
	private int sale_price;
	private Date valid_date;
	public couponListDTO(Coupon cou) {
		this.coupon_name=cou.getCouponName();
		this.coupon_num=cou.getCouponNum();
		this.sale_price=cou.getSalePrice();
		this.valid_date=cou.getValidDate();
	}
}
