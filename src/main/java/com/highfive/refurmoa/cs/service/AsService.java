package com.highfive.refurmoa.cs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.entity.AsStore;

public interface AsService {
	
	public Page<AsStore> listAsStore(Pageable pageable);	//	as 매장 조회
	public AsStore insertAsStore(AsStore asStore);	//	as 매장 등록
	public AsStore updateAsStore(AsStore asStore);	//	as 매장 수정
	public void deleteAsStore(int storeNum);	//	as 매장 삭제
	public Page<AsStore> searchAsStoreCity(String storeAddr, String storeDetail,Pageable pageable);	//	as 매장 지역 검색
	public Page<AsStore> searchAsStoreText(String search,Pageable pageable);	//	as 매장명 검색
	
}
