package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import jakarta.persistence.*;
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
@Entity
public class Delivery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int num;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinColumn(name = "pay_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Payment payment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "deli_num", length = 20)
    private String deliNum;

    @Column(name = "receipt_name", nullable = false, length = 10)
    private String receiptName;

    @Column(name = "receipt_phone", nullable = false, length = 15)
    private String receiptPhone;

    @Column(name = "receipt_addr", nullable = false, length = 50)
    private String receiptAddr;

    @Column(name = "receipt_detail", nullable = false, length = 50)
    private String receiptDetail;

    @Column(name = "receipt_req", length = 50)
    private String receiptReq;

    @Column(name = "deli_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliDate;

    public Delivery(PayRequestDTO payRequestDTO) {
        this.payment = new Payment();
        this.payment.setPayNum(payRequestDTO.getPay_num());
        this.product = new Product();
        this.product.setProductCode(payRequestDTO.getProduct_code());
        this.receiptName = payRequestDTO.getReceipt_name();
        this.receiptPhone = payRequestDTO.getReceipt_phone();
        this.receiptAddr = payRequestDTO.getReceipt_addr();
        this.receiptDetail = payRequestDTO.getReceipt_detail();
        this.receiptReq = payRequestDTO.getReceipt_req();
    }

}
