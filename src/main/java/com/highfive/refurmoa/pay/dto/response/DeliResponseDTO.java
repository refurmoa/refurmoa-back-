package com.highfive.refurmoa.pay.dto.response;

import java.util.Date;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeliResponseDTO {
	private String pay_num;
	private int product_code ;
	private int board_num;
	private String receipt_name;
	private String receipt_phone;
	private String deli_num;
	private int state;
	private Date pay_date;

}
