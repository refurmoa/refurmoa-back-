package com.highfive.refurmoa.cs.dto.response;

import java.util.Date;

import com.highfive.refurmoa.entity.Inquiry;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class InquiryDetailDTO {
	private int num;
    private String memberId;
    private String inqTitle;
    private String inqCon;
    private String inqImg;
    private String inqOrgImg;
    private Date inqDate;
    private String answerCon;
    private Date answerDate;
    public InquiryDetailDTO(Inquiry inquiry) {
    	this.num =inquiry.getNum() ;
    	this.memberId =inquiry.getMemberId().getMemberId() ;
    	this.inqTitle =inquiry.getInqTitle() ;
    	this.inqCon =inquiry.getInqCon() ;
    	this.inqImg =inquiry.getInqImg() ;
    	this.inqOrgImg=inquiry.getInqOrgImg();
    	this.inqDate =inquiry.getInqDate() ;
    	this.answerCon =inquiry.getAnswerCon() ;
    	this.answerDate =inquiry.getAnswerDate() ; 		
    }
}
