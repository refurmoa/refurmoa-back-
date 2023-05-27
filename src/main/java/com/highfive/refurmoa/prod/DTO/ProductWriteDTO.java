package com.highfive.refurmoa.prod.DTO;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWriteDTO {
	
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

}
