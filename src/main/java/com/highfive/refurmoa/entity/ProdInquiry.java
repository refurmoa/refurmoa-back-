package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "prod_inquiry")
public class ProdInquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_inquiry_num", nullable = false)
	private int prodInquiryNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member memberId;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board boardNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Product productCode;

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
