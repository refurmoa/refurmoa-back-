package com.highfive.refurmoa.post.dto;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Userlike;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PostDetailResponseDTO {
    private int product_code;
    private int sell_type;
    private String main_image;
    private String prod_com;
    private String prod_name;
    private String prod_grade;
    private int org_price;
    private int direct_price;
    private int auction_price;
    private int unit_price;
    private boolean guarantee;
    private int as_date;
    private int delivery_price;
    private int cur_price;
    private String detail_image;
    private Date start_date;
    private Date end_date;
    private String defect_text;
    private String defect_image1;
    private String defect_image2;
    private String defect_image3;
    private boolean like;

    public PostDetailResponseDTO(Board board, Userlike userlike) {
        this.product_code = board.getProduct().getProductCode();
        this.sell_type = board.getSellType();
        this.main_image = board.getProduct().getMainImage();
        this.prod_com = board.getProduct().getProdCom();
        this.prod_name = board.getProduct().getProdName();
        this.prod_grade = board.getProduct().getProdGrade();
        this.org_price = board.getProduct().getOrgPrice();
        this.direct_price = board.getDirectPrice();
        this.auction_price = board.getAuctionPrice();
        this.unit_price = board.getUnitPrice();
        this.guarantee = board.getProduct().isGuarantee();
        this.as_date = board.getAsDate();
        this.delivery_price = board.getDeliveryPrice();
        this.cur_price = board.getCurPrice();
        this.detail_image = board.getDetailImage();
        this.start_date = board.getStartDate();
        this.end_date = board.getEndDate();
        this.defect_text = board.getProduct().getDefectText();
        this.defect_image1 = board.getProduct().getDefectImage1();
        this.defect_image2 = board.getProduct().getDefectImage2();
        this.defect_image3 = board.getProduct().getDefectImage3();
        if (userlike != null) this.like = true;
        else this.like = false;
    }
}
