package com.highfive.refurmoa.post.dto;

import com.highfive.refurmoa.entity.ProdInquiry;
import com.highfive.refurmoa.entity.ProdInquiryReply;

import lombok.Getter;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdInqListResponseDTO {
    private int prod_inquiry_num;
    private String member_id;
    private boolean secret;
    private String title;
    private String content;
    private Date date;
    private String re_con;

    public ProdInqListResponseDTO(ProdInquiry prodInquiry, ProdInquiryReply prodInquiryReply) {
        this.prod_inquiry_num = prodInquiry.getProdInquiryNum();
        this.member_id = prodInquiry.getMember().getMemberId();
        this.secret = prodInquiry.isSecret();
        this.title = prodInquiry.getTitle();
        this.content = prodInquiry.getContent();
        this.date = prodInquiry.getDate();
        if (prodInquiryReply != null) { this.re_con = prodInquiryReply.getReCon(); }
        else { this.re_con = ""; }
    }
}
