package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.post.dto.ProdInqReplyRequestDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
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
	private ProdInquiry prodInquiry;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "product_code", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private Product product;

	@Column(name = "re_con", nullable = false, length=500)
	private String reCon;

	@Column(name = "re_date", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date reDate;
		
	@Column(name = "re_update")
	@Temporal(TemporalType.TIMESTAMP)
	private Date reUpdate;

	public ProdInquiryReply(ProdInqReplyRequestDTO prodInqReplyRequestDTO, ProdInquiry prodInquiry) {
		this.prodInquiry = new ProdInquiry();
		this.prodInquiry.setProdInquiryNum(prodInqReplyRequestDTO.getProdInquiryNum());
		this.board = new Board();
		this.board.setBoardNum(prodInquiry.getBoard().getBoardNum());
		this.product = new Product();
		this.product.setProductCode(prodInquiry.getProduct().getProductCode());
		this.reCon = prodInqReplyRequestDTO.getReCon();
		this.reDate = new Date();
		this.reUpdate = null;
	}

}
