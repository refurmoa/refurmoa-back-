package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.post.dto.request.BidRequestDTO;
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
public class AutoBid {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "autobid_num", nullable = false)
    private int autobidNum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_num", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member member;

    @Column(name = "autobid_price", nullable = false)
    private int autobidPrice;

    @Column(name = "autobid_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date autobidDate;

    public AutoBid(BidRequestDTO bidRequestDTO) {
        this.board = new Board();
        this.board.setBoardNum(bidRequestDTO.getBoardNum());
        this.member = new Member();
        this.member.setMemberId(bidRequestDTO.getMemberId());
        this.autobidPrice = bidRequestDTO.getAutobidPrice();
        this.autobidDate = new Date();
    }

}
