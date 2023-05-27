package com.highfive.refurmoa.cs.service;

import java.util.List;

import com.highfive.refurmoa.entity.AsStore;

public interface CsService {
	
	public List<AsStore> listAsStore();
	public AsStore insertAsStore(AsStore asStore);
	public AsStore updateAsStore(int storeNum, AsStore asStore);
	public void deleteAsStore(int storeNum);
	public List<AsStore> searchAsStore(String storeAddr);
	public List<AsStore> searchAsStore2(String storeName);
}
