package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@Table(name = "prod_partner")

public class ProdPartner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "com_num", nullable = false)
    private int comNum;

    @Column(name = "com_name", nullable = false, length = 15)
    private String comName;

    @Column(name = "com_ceo_name", nullable = false, length = 10)
    private String comCeoName;

    @Column(name = "com_phone", nullable = false, length = 20)
    private String comPhone;

    @Column(name = "com_email", nullable = false, length = 30)
    private String comEmail;


}
