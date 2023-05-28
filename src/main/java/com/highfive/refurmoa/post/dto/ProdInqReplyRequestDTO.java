package com.highfive.refurmoa.post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdInqReplyRequestDTO {
    private int prodInquiryNum;
    private String reCon;
}
