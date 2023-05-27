package com.highfive.refurmoa.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "as_store")
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

    @Column(nullable = false, precision = 18, scale = 10)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 18, scale = 10)
    private BigDecimal longitude;

}
