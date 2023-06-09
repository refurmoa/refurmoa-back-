package com.highfive.refurmoa.post.dto.request;

import java.util.Date;
import java.util.Optional;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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
    private int com_num;
    private int board_num;
	private int sell_type;
	private Optional<Date> start_date;
	private Optional<Date> end_date;
	private int del_price;
	private int auc_price;
	private int dir_price;
	private int unit_price;
	private int as_date;
	
}
