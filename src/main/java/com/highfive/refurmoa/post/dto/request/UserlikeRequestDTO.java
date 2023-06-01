package com.highfive.refurmoa.post.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserlikeRequestDTO {
    String memberId;
    int boardNum;
    boolean like;
}
