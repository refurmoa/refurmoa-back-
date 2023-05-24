package com.highfive.refurmoa.entity;

import java.util.Date;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
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

    @Column(name = "use_check")
    private boolean useCheck;

    @Column(name = "use_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date useDate;

}
