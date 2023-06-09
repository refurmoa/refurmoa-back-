package com.highfive.refurmoa.pay.dto.response;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Delivery;
import com.highfive.refurmoa.entity.Payment;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PayDetailResponseDTO {
    private String pay_num;
    private String main_image;
    private String prod_com;
    private String prod_grade;
    private String prod_name;
    private int prod_state;
    private String receipt_name;
    private String receipt_phone;
    private String receipt_addr;
    private String receipt_detail;
    private String receipt_req;
    private String deli_num;
    private int prod_price;
    private int delivery_price;
    private int coupon_price;
    private int mile_use;
    private int pay_price;
    private String buy_method;
    public PayDetailResponseDTO(Payment payment, Delivery delivery, Optional<Coupon> coupon) {
        this.pay_num = payment.getPayNum();
        this.main_image = payment.getProduct().getMainImage();
        this.prod_com = payment.getProduct().getProdCom();
        this.prod_grade = payment.getProduct().getProdGrade();
        this.prod_name = payment.getProduct().getProdName();
        this.prod_state = payment.getProduct().getProdState();
        this.receipt_name = delivery.getReceiptName();
        this.receipt_phone = delivery.getReceiptPhone();
        this.receipt_addr = delivery.getReceiptAddr();
        this.receipt_detail = delivery.getReceiptDetail();
        this.receipt_req = delivery.getReceiptReq();
        this.deli_num = delivery.getDeliNum();
        this.prod_price = payment.getProdPrice();
        this.delivery_price = payment.getDeliveryPrice();
        this.coupon_price = coupon.orElseGet(() -> new Coupon(0)).getSalePrice();
        if (payment.getMileUse() != null) {
            this.mile_use = payment.getMileUse();
        } else {
            this.mile_use = 0;
        }
        this.pay_price = payment.getPayPrice();
        this.buy_method = payment.getBuyMethod();
    }
}
