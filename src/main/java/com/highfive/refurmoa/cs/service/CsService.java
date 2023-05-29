package com.highfive.refurmoa.cs.service;

import java.util.List;

import com.highfive.refurmoa.entity.AsStore;

public interface CsService {
	
	public List<AsStore> listAsStore();
	public AsStore insertAsStore(AsStore asStore);
	public AsStore updateAsStore(AsStore asStore);
	public void deleteAsStore(int storeNum);
	public List<AsStore> searchAsStoreCity(String storeAddr);
	public List<AsStore> searchAsStoreText(String storeName);
}
