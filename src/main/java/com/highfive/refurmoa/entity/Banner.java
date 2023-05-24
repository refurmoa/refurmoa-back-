package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bann_num", nullable = false)
    private int bannNum;

    @Column(name = "bann_image", nullable = false, length = 50)
    private String bannImage;

    @Column(name = "seller_name", nullable = false, length = 15)
    private String sellerName;

    @Column(name = "seller_phone", nullable = false, length = 15)
    private String sellerPhone;

    @Column(name = "bann_link", length = 200)
    private String bannLink;

    @Column(name = "bann_ref", length = 50)
    private String bannRef;

    @Column(name = "bann_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bannStart;

    @Column(name = "bann_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bannEnd;
}
