package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Payment {

    @Id
    @Column(name = "pay_num", nullable = false, length = 20)
    private String payNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "prod_price", nullable = false)
    private int prodPrice;

    @Column(name = "delivery_price", nullable = false)
    private int deliveryPrice;

    @Column(name = "pay_price", nullable = false)
    private int payPrice;

    @Column(name = "buy_method", nullable = false, length = 10)
    private String buyMethod;

    @Column(name = "coupon_num")
    private Integer couponNum;

    @Column(name = "mile_use")
    private Integer mileUse;

    @Column(name = "pay_date")
    private Date payDate;

    @Column(name = "pay_cancel", nullable = false)
    @ColumnDefault(value = "false")
    private boolean payCancel;

    public Payment(PayRequestDTO payRequestDTO) {
        this.payNum = payRequestDTO.getPay_num();
        this.member = new Member();
        this.member.setMemberId(payRequestDTO.getMember_id());
        this.board = new Board();
        this.board.setBoardNum(payRequestDTO.getBoard_num());
        this.product = new Product();
        this.product.setProductCode(payRequestDTO.getProduct_code());
        this.prodPrice = payRequestDTO.getProd_price();
        this.deliveryPrice = payRequestDTO.getDelivery_price();
        this.payPrice = payRequestDTO.getPay_price();
        this.buyMethod = payRequestDTO.getBuy_method();
        if (payRequestDTO.getCoupon_num() != 0)
            this.couponNum = payRequestDTO.getCoupon_num();
        if (payRequestDTO.getMile_use() != 0)
            this.mileUse = payRequestDTO.getMile_use();
        this.payDate = new Date();
    }
}
