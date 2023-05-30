package com.highfive.refurmoa.post.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class BidRequestDTO {
    String memberId;
    int boardNum;
    Integer autobidPrice;
    int bidPrice;
    int unitPrice;
}
