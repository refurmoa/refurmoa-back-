package com.highfive.refurmoa.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.AsStore;


public interface AsRepository extends JpaRepository<AsStore, Integer> {

		
	@Query(value="select * from as_store where store_addr like %:storeAddr% and store_addr like :storeDetail%", nativeQuery=true)
	List<AsStore> findByStoreAddrAndStoreDetail(@Param("storeAddr") String storeAddr, @Param("storeDetail") String storeDetail);	// as 매장 지역 검색
	
	List<AsStore> findByStoreNameContaining(String storeName);	// as 매장명 검색
	
}

