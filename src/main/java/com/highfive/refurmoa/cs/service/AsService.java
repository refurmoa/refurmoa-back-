package com.highfive.refurmoa.cs.service;

import java.util.List;

import com.highfive.refurmoa.entity.AsStore;

public interface AsService {
	
	public List<AsStore> listAsStore();	//	as 매장 조회
	public AsStore insertAsStore(AsStore asStore);	//	as 매장 등록
	public AsStore updateAsStore(AsStore asStore);	//	as 매장 수정
	public void deleteAsStore(int storeNum);	//	as 매장 삭제
	public List<AsStore> searchAsStoreCity(String storeAddr, String storeDetail);	//	as 매장 지역 검색
	public List<AsStore> searchAsStoreText(String storeName);	//	as 매장명 검색
	
}
