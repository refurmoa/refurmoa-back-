package com.highfive.refurmoa.prod.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdFileDTO {
	private int prodNum;
	private String main_image;
    private String up_main_image;
	private String deffect_image1;
	private String up_deffect_image1;
    private String deffect_image2;
    private String up_deffect_image2;
    private String deffect_image3;
    private String up_deffect_image3;
}
