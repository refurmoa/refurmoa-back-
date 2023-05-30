package com.highfive.refurmoa.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.AsStore;


public interface AsRepository extends JpaRepository<AsStore, Integer> {

	
	List<AsStore> findByStoreAddrContaining(String storeAddr);	// as 매장 지역 검색
	
	List<AsStore> findByStoreNameContaining(String storeName);	// as 매장명 검색
	
}

