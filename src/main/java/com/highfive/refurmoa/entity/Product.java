package com.highfive.refurmoa.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;

import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Product {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code", nullable = false)
    private int productCode;


    @Column(name = "category", nullable = false, length = 20)
    private String category;
    
    @Column(name = "category_code", nullable = false, length = 10)
    private String categoryCode;
    
    @Column(name = "defect_image1", length = 50)
    private String defectImage1;

    @Column(name = "defect_image2", length = 50)
    private String defectImage2;

    @Column(name = "defect_image3", length = 50)
    private String defectImage3;
    
    @Column(name = "defect_text", nullable = false, length = 200)
    private String defectText;
    
    @Column(nullable = false)
    private boolean guarantee;

    @Column(name = "main_image", nullable = false, length = 50)
    private String mainImage;
    
    @Column(name = "org_price", nullable = false)
    private int orgPrice;

    @Column(name = "prod_com", nullable = false, length = 15)
    private String prodCom;

    @Column(name = "prod_grade", nullable = false, length = 1)
    private String prodGrade;
    
    @Column(name = "prod_name", nullable = false, length = 30)
    private String prodName;

    @Column(name = "prod_state", nullable = false)
    private int prodState;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @JoinColumn(name = "com_num", nullable = false)
    private int comNum;
    // @Id
    // @GeneratedValue(strategy = GenerationType.IDENTITY)
    // @Column(name = "product_code", nullable = false)
    // private int productCode;

    // @ManyToOne(fetch = FetchType.LAZY)
    // @JoinColumn(name = "com_num")
    // private ProdPartner comNum;

    // @Column(name = "category_code", nullable = false, length = 10)
    // private String categoryCode;

    // @Column(nullable = false, length = 20)
    // private String category;

    // @Column(name = "main_image", nullable = false, length = 50)
    // private String mainImage;

    // @Column(name = "prod_com", nullable = false, length = 15)
    // private String prodCom;

    // @Column(name = "prod_name", nullable = false, length = 30)
    // private String prodName;

    // @Column(name = "prod_grade", nullable = false, length = 1)
    // private String prodGrade;

    // @Column(name = "org_price", nullable = false)
    // private int orgPrice;

    // @Column(nullable = false)
    // private boolean guarantee;

    // @Column(name = "defect_text", nullable = false, length = 200)
    // private String defectText;

    // @Column(name = "defect_image1", length = 50)
    // private String defectImage1;

    // @Column(name = "defect_image2", length = 50)
    // private String defectImage2;

    // @Column(name = "defect_image3", length = 50)
    // private String defectImage3;

    // @Column(name = "reg_date", nullable = false)
    // @Temporal(TemporalType.TIMESTAMP)
    // private Date regDate;

    // @Column(name = "prod_state", nullable = false)
    // private int prodState;

}
