package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@Entity
public class Payment {

    @Id
    @Column(name = "pay_num", nullable = false)
    private int payNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member memberId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board boardNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product productCode;

    @Column(name = "prod_price", nullable = false)
    private int prodPrice;

    @Column(name = "delivery_price", nullable = false)
    private int deliveryPrice;

    @Column(name = "pay_price", nullable = false)
    private int payPrice;

    @Column(name = "buy_method", nullable = false, length = 10)
    private String buyMethod;

    @Column(name = "coupon_num")
    private int couponNum;

    @Column(name = "mile_use")
    private int mileUse;

    @Column(name = "as_date")
    private Date asDate;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "pay_cancel", nullable = false)
    private boolean payCancel;

}
