package com.highfive.refurmoa.prod.DTO.response;

import com.highfive.refurmoa.entity.Product;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ProdListResponseDTO {
    private int product_code;
    private String category_code;
    private Date reg_date;
    private String main_image;
    private String com_name;
    private String prod_com;
    private String prod_grade;
    private String prod_name;
    private int org_price;
    private boolean guarantee;
    private String defect_text;
    private String defect_image1;
    private String defect_image2;
    private String defect_image3;
    private int sell_status;

    public ProdListResponseDTO(Product product, Date startDate, String comName) {
        this.product_code = product.getProductCode();
        this.category_code = product.getCategoryCode();
        this.reg_date = product.getRegDate();
        this.main_image = product.getMainImage();
        this.com_name = comName;
        this.prod_com = product.getProdCom();
        this.prod_grade = product.getProdGrade();
        this.prod_name = product.getProdName();
        this.org_price = product.getOrgPrice();
        this.guarantee = product.isGuarantee();
        this.defect_text = product.getDefectText();
        this.defect_image1 = product.getDefectImage1();
        this.defect_image2 = product.getDefectImage2();
        this.defect_image3 = product.getDefectImage3();

        switch (product.getProdState()) {
            case 0 -> { this.sell_status = 0; }
            case 1 -> {
                if (startDate != null && startDate.after(new Date()))
                    this.sell_status = 1;
                else this.sell_status = 2;
            }
            case 2, 3, 4 -> { this.sell_status = 3; }
            case 5 -> { this.sell_status = 4; }
        }
    }
}
