package com.highfive.refurmoa.pay.repository;

import com.highfive.refurmoa.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository <Delivery, Integer> {
    Delivery findByProductProductCode(int productCode);
}
