package com.highfive.refurmoa.post.dto.reponse;

import java.util.Date;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MyListDTO {
	private int board_num;
	private int sell_type;
	private String main_image;
	private Date start_date;
	private Date end_date;
	private String prod_com;
	private String prod_name;
	private int prod_state;
	private int orgPrice;
	private int curPrice;
	private int directPrice;
	public MyListDTO(Board board) {
		this.board_num = board.getBoardNum();
		this.sell_type =board.getSellType() ;
		this.main_image =board.getProduct().getMainImage() ;
		this.start_date =board.getStartDate() ;
		this.end_date =board.getEndDate() ;
		this.prod_state=board.getProduct().getProdState();
		this.prod_com =board.getProduct().getProdCom();
		this.prod_name =board.getProduct().getProdName();
		this.orgPrice =board.getProduct().getOrgPrice();
		this.curPrice = board.getCurPrice();
		if(board.getDirectPrice()==null)this.directPrice= 0;
		else this.directPrice=board.getDirectPrice();
	}
}
