package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bid_num", nullable = false)
    private int bidNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "board_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "bid_price", nullable = false)
    private int bidPrice;

    @Column(name = "bid_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bidDate;

    @Column(name = "bid_auto", nullable = false)
    private boolean bidAuto;

    @Column(name = "bid_cancel", nullable = false)
    private boolean bidCancel;

}