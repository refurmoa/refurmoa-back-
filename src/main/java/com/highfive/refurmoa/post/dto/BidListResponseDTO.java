package com.highfive.refurmoa.post.dto;

import com.highfive.refurmoa.entity.Bid;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidListResponseDTO {
    private String member_id;
    private int bid_price;
    private Date bid_date;
    private boolean bid_auto;

    public BidListResponseDTO(Bid bid) {
        this.member_id = bid.getMember().getMemberId();
        this.bid_price = bid.getBidPrice();
        this.bid_date = bid.getBidDate();
        this.bid_auto = bid.isBidAuto();
    }
}