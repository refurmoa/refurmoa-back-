package com.highfive.refurmoa.prod.DTO;

import java.util.Date;

import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdListDTO {
	
	private int productCode;
	private String category;
	private String categoryCode;
	private String defectImage1;
    private String defectImage2;
    private String defectImage3;
    private String defectText;
    private boolean guarantee;
    private String mainImage;
    private int orgPrice;
    private String prodCom;
    private String prodGrade;
    private String prodName;
    private int prodState;
    private Date regDate;
	private String com_name;
	private int com_num;
	private int sell_status;
	public ProdListDTO(Product dto,int num) {
		this.productCode = dto.getProductCode() ;
		this.category = dto.getCategory() ;
		this.categoryCode = dto.getCategoryCode();
		this.mainImage=dto.getMainImage();
		this.defectImage1 = dto.getDefectImage1() ;
		this.defectImage2 = dto.getDefectImage2();
		this.defectImage3 = dto.getDefectImage3() ;
		this.defectText = dto.getDefectText() ;
		this.guarantee = dto.isGuarantee() ;
		this.orgPrice = dto.getOrgPrice() ;
		this.prodCom = dto.getProdCom() ;
		this.prodGrade = dto.getProdGrade() ;
		this.prodName = dto.getProdName() ;
		this.prodState = dto.getProdState() ;
		this.regDate = dto.getRegDate() ;
		this.com_name = dto.getComNum().getComName() ;
		this.com_num = dto.getComNum().getComNum();
		this.sell_status=num;
	}
}
