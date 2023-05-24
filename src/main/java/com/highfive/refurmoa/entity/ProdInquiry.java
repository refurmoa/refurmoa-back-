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
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class ProdInquiry {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_inquiry_num", nullable = false)
	private int prodInquiryNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member memberId;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num")
	private Board boardNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code")
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Board productCode;

	@Column(name = "secret", nullable = false)
	private boolean secret;

	@Column(name = "title", nullable = false, length=20)
	private String title;

	@Column(name = "content", nullable = false, length=500)
	private String content;

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date Date;
	  	
}
