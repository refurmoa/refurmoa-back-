package com.highfive.refurmoa.post.dto.reponse;

import java.util.Date;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostInfoDTO {
	private FindProductDTO prod;
	private int sell_type;
	private Date start_date;
	private Date end_date;
	private int del_price;
	private int auc_price;
	private int dir_price;
	private int unit_price;
	private int as_date;
	private String detail;
	public PostInfoDTO(FindProductDTO prod,Board board) {
		this.prod=prod;
		this.sell_type=board.getSellType();
		this.start_date = board.getStartDate() ;
		this.end_date = board.getEndDate() ;
		this.del_price = board.getDeliveryPrice() ;
		this.auc_price = board.getAuctionPrice() ;
		this.dir_price = board.getDirectPrice() ;
		this.as_date = board.getAsDate() ;
		this.unit_price= board.getUnitPrice();
		this.detail=board.getDetailImage();
	}
}
