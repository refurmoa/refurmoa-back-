package com.highfive.refurmoa.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.highfive.refurmoa.user.DTO.request.CouponRegiDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@NoArgsConstructor
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "coupon_num", nullable = false)
    private int couponNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member memberId;

    @Column(name = "coupon_name", nullable = false, length = 20)
    private String couponName;

    @Column(name = "sale_price", nullable = false)
    private int salePrice;

    @Column(name = "issue_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;

    @Column(name = "valid_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date validDate;

    @Column(name = "use_check", nullable = false)
    private boolean useCheck;

    @Column(name = "use_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date useDate;

    public Coupon(int sale_price) {
        this.salePrice = sale_price;
    }
    public Coupon (Member mem,CouponRegiDTO dto) {
    	this.memberId=mem;
    	this.couponName=dto.getCouponName();
    	this.issueDate=dto.getIssueDate();
    	this.salePrice=dto.getSalePrice();
    	this.validDate=dto.getValidDate();
    }
}
