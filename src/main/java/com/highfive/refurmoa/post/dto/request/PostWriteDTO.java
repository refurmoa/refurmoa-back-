package com.highfive.refurmoa.post.dto.request;

import java.util.Date;

import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostWriteDTO {
	private int product_code;
	private String category;
	private String category_code;
	private String deffect_image1;
    private String deffect_image2;
    private String deffect_image3;
    private String deffect_text;
    private boolean guarantee;
    private int org_price;
    private String prod_com;
    private String prod_grade;
    private String prod_name;
    private int prod_state;
    private Date reg_date;
    private int com_num;
    private int board_num;
	private int sell_type;
	private Date start_date;
	private Date end_date;
	private int del_price;
	private int auc_price;
	private int dir_price;
	private int unit_price;
	private int readCount;
	private int as_date;
	private boolean deleteCheck;
	
}
