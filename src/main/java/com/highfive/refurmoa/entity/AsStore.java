package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;


@Getter
@Setter
@Table(name = "as_store")
@Entity
public class AsStore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_num", nullable = false)
    private int storeNum;

    @Column(name = "store_name", nullable = false, length = 20)
    private String storeName;

    @Column(name = "store_phone", nullable = false, length = 15)
    private String storePhone;

    @Column(name = "store_addr", nullable = false, length = 100)
    private String storeAddr;

    @Column(name = "store_detail", nullable = false, length = 100)
    private String storeDetail;

    @Column(nullable = false)
    private BigDecimal latitude;

    @Column(nullable = false)
    private BigDecimal longitude;

}
