package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.post.dto.request.BidRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
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
    @ColumnDefault(value = "false")
    private boolean bidCancel;

    public Bid(BidRequestDTO bidRequestDTO) { // 입찰
        this.board = new Board();
        this.board.setBoardNum(bidRequestDTO.getBoardNum());
        this.member = new Member();
        this.member.setMemberId(bidRequestDTO.getMemberId());
        this.bidPrice = bidRequestDTO.getBidPrice();
        this.bidDate = new Date();
        this.bidAuto = false;
    }

    public Bid(AutoBid autoBid, int price) { // 자동입찰
        this.board = new Board();
        this.board.setBoardNum(autoBid.getBoard().getBoardNum());
        this.member = new Member();
        this.member.setMemberId(autoBid.getMember().getMemberId());
        this.bidPrice = price;
        this.bidDate = new Date();
        this.bidAuto = true;
    }

}