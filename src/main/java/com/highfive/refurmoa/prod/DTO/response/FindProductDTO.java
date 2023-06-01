package com.highfive.refurmoa.prod.DTO.response;

import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FindProductDTO {
	private int productCode;
	private String category;
	private String categoryCode;
    private String mainImage;
    private int orgPrice;
    private String prodCom;
    private String prodGrade;
    private String prodName;
    public FindProductDTO(Product dto) {
    	this.productCode = dto.getProductCode() ;
    	this.category = dto.getCategory() ;
    	this.categoryCode = dto.getCategoryCode() ;
    	this.orgPrice = dto.getOrgPrice() ;
    	this.mainImage= dto.getMainImage() ;
    	this.prodCom = dto.getProdCom() ;
    	this.prodGrade = dto.getProdGrade() ;
    	this.prodName = dto.getProdName() ;
    }
}
