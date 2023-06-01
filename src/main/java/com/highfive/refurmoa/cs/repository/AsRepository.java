package com.highfive.refurmoa.cs.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.AsStore;


public interface AsRepository extends JpaRepository<AsStore, Integer> {

	@Query("select p from AsStore p where p.storeAddr like %:storeAddr% and p.storeAddr like %:storeDetail%")
	Page<AsStore> findByStoreAddrAndStoreDetail(@Param("storeAddr") String storeAddr, @Param("storeDetail") String storeDetail,Pageable pageable);	// as 매장 지역 검색
	
	Page<AsStore> findByStoreNameContaining(String storeName,Pageable pageable);	// as 매장명 검색
	
	Page<AsStore> findAll(Pageable pageable);

}