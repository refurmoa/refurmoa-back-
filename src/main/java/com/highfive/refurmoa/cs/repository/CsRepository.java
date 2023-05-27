package com.highfive.refurmoa.cs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.AsStore;


public interface CsRepository extends JpaRepository<AsStore, Integer> {

	
	List<AsStore> findByStoreAddrContaining(String storeAddr);
	
	List<AsStore> findByStoreNameContaining(String storeName);
}
