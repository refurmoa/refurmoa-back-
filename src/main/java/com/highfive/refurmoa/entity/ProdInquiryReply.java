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
@Table(name = "prod_inquiry_reply")
public class ProdInquiryReply {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "re_num", nullable = false)
	private int reNum;

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "prod_inquiry_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private ProdInquiry prodInquiryNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board boardNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Product productCode;

	@Column(name = "re_con", nullable = false, length=500)
	private String reContent;

	@Column(name = "re-date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date Date;
		
	@Column(name = "re_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date ReDate;

}
