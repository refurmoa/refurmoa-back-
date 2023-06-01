package com.highfive.refurmoa.cs.dto.response;

import java.util.Date;

import com.highfive.refurmoa.entity.Inquiry;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InquiryListDTO {
	private int num;
    private String memberId;
    private String inqTitle;
    private Date inqDate;
    private int answerState;
    public InquiryListDTO(Inquiry inquiry) {
    	this.num =inquiry.getNum() ;
    	this.memberId =inquiry.getMemberId().getMemberId() ;
    	this.inqTitle =inquiry.getInqTitle() ;
    	this.inqDate =inquiry.getInqDate() ;
    	if(inquiry.getAnswerCon()==null) {
    		this.answerState=0;
    	}
    	else this.answerState=1;
    }
}
