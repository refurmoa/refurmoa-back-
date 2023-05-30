package com.highfive.refurmoa.cs.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.cs.repository.AsRepository;
import com.highfive.refurmoa.entity.AsStore;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AsServiceImpl implements AsService {
	
private final AsRepository repository;

//	as 매장 조회
	@Override
	public List<AsStore> listAsStore() {
		return (List<AsStore>)repository.findAll();
	}

//	as 매장 등록
	@Override
	public AsStore insertAsStore(AsStore asStore) {
		return repository.save(asStore);
	}
	
//	as 매장 수정
	@Override
	public AsStore updateAsStore(AsStore asStore) {
		return repository.save(asStore);
	}

//	as 매장 삭제
	@Override
	public void deleteAsStore(int storeNum) {
		repository.deleteById(storeNum);
	}
	
//	as 매장 지역 검색
	@Override
	public List<AsStore> searchAsStoreCity(String storeAddr) {
		return (List<AsStore>)repository.findByStoreAddrContaining(storeAddr);
	}

//	as 매장명 검색
	@Override
	public List<AsStore> searchAsStoreText(String storeName) {
		return (List<AsStore>)repository.findByStoreNameContaining(storeName);
	}
	

}

