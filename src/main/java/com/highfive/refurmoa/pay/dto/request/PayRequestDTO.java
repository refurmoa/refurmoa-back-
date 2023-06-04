package com.highfive.refurmoa.pay.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PayRequestDTO {

    private String pay_num;
    private String member_id;
    private int board_num;
    private int product_code;
    private int prod_price;
    private int delivery_price;
    private int pay_price;
    private String buy_method;
    private Integer coupon_num;
    private int mile_use;
    private String receipt_name;
    private String receipt_phone;
    private String receipt_addr;
    private String receipt_detail;
    private String receipt_req;

}
