package com.highfive.refurmoa.admin.dto.response;

import com.highfive.refurmoa.entity.ProdPartner;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerDTO {
	private int com_num;
	private String com_name;
    private String com_ceo_name;
    private String com_email;
    private String com_addr;
    private String com_detail_addr;
    private String com_phone;
    private int com_status;
    private int prod_cnt;
    
    public PartnerDTO(ProdPartner partner,int cnt) {
    	
    	this.com_num=partner.getComNum();
    	this.com_name=partner.getComName();
    	this.com_ceo_name=partner.getComCeoName();
    	this.com_phone=partner.getComPhone();
    	this.com_status=partner.getComStatus();
    	this.prod_cnt=cnt;
    	this.com_email=partner.getComEmail();
    	this.com_addr=partner.getComAddr();
    	this.com_status=partner.getComStatus();
    	this.com_detail_addr=partner.getComDetailAddr();
    }
}
