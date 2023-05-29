package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.post.dto.ProdInqRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prod_inquiry")
public class ProdInquiry {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "prod_inquiry_num", nullable = false)
	private int prodInquiryNum;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id")
	private Member member;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;
		
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Product product;

	@Column(name = "secret", nullable = false)
	private boolean secret;

	@Column(name = "title", nullable = false, length=20)
	private String title;

	@Column(name = "content", nullable = false, length=500)
	private String content;

	@Column(name = "date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;

	public ProdInquiry(ProdInqRequestDTO prodInquiryDTO) {
		this.member = new Member();
		this.member.setMemberId(prodInquiryDTO.getMemberId());
		this.board = new Board();
		this.board.setBoardNum(prodInquiryDTO.getBoardNum());
		this.product = new Product();
		this.product.setProductCode(prodInquiryDTO.getProductCode());
		this.secret = prodInquiryDTO.isSecret();
		this.title = prodInquiryDTO.getTitle();
		this.content = prodInquiryDTO.getContent();
		this.date = new Date();
	}

}
