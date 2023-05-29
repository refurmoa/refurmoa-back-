package com.highfive.refurmoa.post.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class ProdInqRequestDTO {
    private String memberId;
    private int boardNum;
    private int productCode;
    private boolean secret;
    private String title;
    private String content;
}
