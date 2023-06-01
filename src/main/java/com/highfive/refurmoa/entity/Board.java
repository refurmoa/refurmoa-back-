package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "board_num", nullable = false)
    private int boardNum;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_code", nullable = false)
    @OnDelete(action = OnDeleteAction.NO_ACTION)
    private Product product;

    @Column(name = "sell_type", nullable = false)
    private int sellType;

    @Column(name = "direct_price")
    private Integer directPrice;

    @Column(name = "auction_price")
    private Integer auctionPrice;

    @Column(name = "unit_price")
    private Integer unitPrice;

    @Column(name = "cur_price", nullable = false)
    private int curPrice;

    @Column(name = "as_date", nullable = false)
    private int asDate;

    @Column(name = "delivery_price", nullable = false)
    private int deliveryPrice;

    @Column(name = "detail_image", nullable = false, length = 100)
    private String detailImage;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "read_count", nullable = false)
    private int readCount;

    @Column(name = "update_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateDate;

    @Column(name = "delete_check", nullable = false)
    private boolean deleteCheck;

}
