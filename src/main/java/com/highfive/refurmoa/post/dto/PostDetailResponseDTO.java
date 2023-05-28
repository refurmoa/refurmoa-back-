package com.highfive.refurmoa.post.dto;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Userlike;
import lombok.Getter;
import lombok.AllArgsConstructor;

import java.util.Date;

@Getter
@AllArgsConstructor
public class PostDetailResponseDTO {
    private int productCode;
    private int sellType;
    private String mainImage;
    private String prodCom;
    private String prodName;
    private String prodGrade;
    private int orgPrice;
    private int directPrice;
    private int auctionPrice;
    private int unitPrice;
    private boolean guarantee;
    private int asDate;
    private int deliveryPrice;
    private int curPrice;
    private String detailImage;
    private Date startDate;
    private Date endDate;
    private String defectText;
    private String defectImage1;
    private String defectImage2;
    private String defectImage3;
    private boolean like;

    public PostDetailResponseDTO(Board board, Userlike userlike) {
        this.productCode = board.getProduct().getProductCode();
        this.sellType = board.getSellType();
        this.mainImage = board.getProduct().getMainImage();
        this.prodCom = board.getProduct().getProdCom();
        this.prodName = board.getProduct().getProdName();
        this.orgPrice = board.getProduct().getOrgPrice();
        this.directPrice = board.getDirectPrice();
        this.auctionPrice = board.getAuctionPrice();
        this.unitPrice = board.getUnitPrice();
        this.guarantee = board.getProduct().isGuarantee();
        this.asDate = board.getAsDate();
        this.deliveryPrice = board.getDeliveryPrice();
        this.curPrice = board.getCurPrice();
        this.detailImage = board.getDetailImage();
        this.startDate = board.getStartDate();
        this.endDate = board.getEndDate();
        this.defectText = board.getProduct().getDefectText();
        this.defectImage1 = board.getProduct().getDefectImage1();
        this.defectImage2 = board.getProduct().getDefectImage2();
        this.defectImage3 = board.getProduct().getDefectImage3();
        if (userlike != null) this.like = true;
        else this.like = false;
    }
}
