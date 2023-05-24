package com.highfive.refurmoa.entity;

import java.util.Date;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ProdInquiryReply {
	
	
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "re_num", nullable = false)
	 	private int reNum;
	
		@OneToOne(fetch = FetchType.EAGER)
	    @JoinColumn(name = "prod_inquiry_num")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private ProdInquiry prodInquiryNum;
	  
	  	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "member_id")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Member memberId;
	  	
	  	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "board_num")
	    private Board boardNum;
	  	
	  	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "product_code")
	    @OnDelete(action = OnDeleteAction.RESTRICT)
	    private Board productCode;
	  
	  	@Column(name = "re_con", nullable = false, length=500)
	    private String reContent;
	  	
	  	 @Column(name = "re-date")
	     @Temporal(TemporalType.TIMESTAMP)
	     private Date Date;
	  	 
	  	@Column(name = "re_update")
	     @Temporal(TemporalType.TIMESTAMP)
	     private Date ReDate;
}
