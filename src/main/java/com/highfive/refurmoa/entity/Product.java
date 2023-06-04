package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_code", nullable = false)
    private int productCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "com_num")
    private ProdPartner prodPartner;

    @Column(name = "category_code", nullable = false, length = 10)
    private String categoryCode;

    @Column(nullable = false, length = 20)
    private String category;

    @Column(name = "main_image", nullable = false, length = 100)
    private String mainImage;

    @Column(name = "prod_com", nullable = false, length = 15)
    private String prodCom;

    @Column(name = "prod_name", nullable = false, length = 30)
    private String prodName;

    @Column(name = "prod_grade", nullable = false, length = 1)
    private String prodGrade;

    @Column(name = "org_price", nullable = false)
    private int orgPrice;

    @Column(nullable = false)
    private boolean guarantee;

    @Column(name = "defect_text", nullable = false, length = 200)
    private String defectText;

    @Column(name = "defect_image1", length = 100)
    private String defectImage1;

    @Column(name = "defect_image2", length = 100)
    private String defectImage2;

    @Column(name = "defect_image3", length = 100)
    private String defectImage3;

    @Column(name = "reg_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date regDate;

    @Column(name = "prod_state", nullable = false)
    private int prodState;

}
