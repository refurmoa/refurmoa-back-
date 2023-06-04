package com.highfive.refurmoa.pay.repository;

import com.highfive.refurmoa.entity.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DeliveryRepository extends JpaRepository <Delivery, Integer> {
    Delivery findByProductProductCode(int productCode);
    
    // 배송중
    @Query("SELECT COUNT(d) FROM Delivery d " +
            "WHERE d.deliDate IS NULL " +
            "AND d.deliNum IS NOT NULL"
    )
    Long getAdminCountShipping();

    // 배송완료
    @Query("SELECT COUNT(d) FROM Delivery d LEFT JOIN product p " +
            "WHERE d.deliDate IS NOT NULL " +
            "AND p.prodState != 5"
    )
    Long getAdminCountCompleted();

    // 상품준비중
    @Query("SELECT COUNT(d) FROM Delivery d LEFT JOIN payment p " +
            "WHERE d.deliNum IS NULL " +
            "AND p.payCancel = FALSE"
    )
    Long getPrepareCount();
}
