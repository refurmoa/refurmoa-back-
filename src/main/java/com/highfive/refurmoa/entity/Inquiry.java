package com.highfive.refurmoa.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.highfive.refurmoa.cs.dto.request.InquiryReplyDTO;
import com.highfive.refurmoa.cs.dto.request.InquiryWriteDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member memberId;

    @Column(name = "inq_title", nullable = false, length = 50)
    private String inqTitle;

    @Column(name = "inq_con", nullable = false, length = 500)
    private String inqCon;

    @Column(name = "inq_img", length = 100)
    private String inqImg;
    
    @Column(name = "inq_org_img", length = 100)
    private String inqOrgImg;

    @Column(name = "inq_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inqDate;

    @Column(name = "answer_con", length = 500)
    private String answerCon;

    @Column(name = "answer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;
    
    public Inquiry(Inquiry org,InquiryReplyDTO dto) {
    	this.num=dto.getNum();
    	this.memberId=org.getMemberId();
    	this.inqCon=org.getInqCon();
    	this.inqTitle=org.getInqTitle();
    	this.inqImg=org.getInqImg();
    	this.inqOrgImg=org.getInqOrgImg();
    	this.inqDate=org.getInqDate();
    	this.answerCon=dto.getAnswerCon();
    	this.answerDate=dto.getAnswerDate();
    }
    public Inquiry(String img,String orgimg,InquiryWriteDTO write,Member mem) {
    	
    	this.memberId=mem;
    	this.inqCon=write.getInqCon();
    	this.inqTitle=write.getInqTitle();
    	this.inqImg=img;
    	this.inqOrgImg=orgimg;
    	this.inqDate=write.getInqDate(); 
    }
    public Inquiry(InquiryWriteDTO write,Member mem) {
    	this.memberId=mem;
    	this.inqCon=write.getInqCon();
    	this.inqTitle=write.getInqTitle();
    	this.inqDate=write.getInqDate(); 
    }
}