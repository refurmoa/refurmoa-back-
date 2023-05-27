package com.highfive.refurmoa.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.cs.repository.CsRepository;
import com.highfive.refurmoa.entity.AsStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CsServiceImpl implements CsService {
	
private final CsRepository repository;

	@Override
	public List<AsStore> listAsStore() {
		return (List<AsStore>)repository.findAll();
	}

	@Override
	public AsStore insertAsStore(AsStore asStore) {
		return repository.save(asStore);
	}
	
	@Override
	public AsStore updateAsStore(int storeNum, AsStore asStore) {
		AsStore existingAsStore = repository.findById(storeNum).get();
		existingAsStore.setStoreName(asStore.getStoreName());
		existingAsStore.setStorePhone(asStore.getStorePhone());
		existingAsStore.setStoreAddr(asStore.getStoreAddr());
		existingAsStore.setStoreDetail(asStore.getStoreDetail());
		existingAsStore.setLatitude(asStore.getLatitude());
		existingAsStore.setLongitude(asStore.getLongitude());
		return repository.save(existingAsStore);
	}

	@Override
	public void deleteAsStore(int storeNum) {
		repository.deleteById(storeNum);
	}
	
	@Override
	public List<AsStore> searchAsStore(String storeAddr) {
		return (List<AsStore>)repository.findByStoreAddrContaining(storeAddr);
	}

	@Override
	public List<AsStore> searchAsStore2(String storeName) {
		return (List<AsStore>)repository.findByStoreNameContaining(storeName);
	}
}
