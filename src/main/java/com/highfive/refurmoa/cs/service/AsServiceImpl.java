package com.highfive.refurmoa.cs.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
	public Page<AsStore> listAsStore(Pageable pageable) {
		return repository.findAllByOrderByStoreName(pageable);
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
	public Page<AsStore> searchAsStoreCity(String storeAddr, String storeDetail,Pageable pageable) {
		return repository.findByStoreAddrAndStoreDetail(storeAddr, storeDetail,pageable);
	}

//	as 매장명 검색
	@Override
	public Page<AsStore> searchAsStoreText(String search,Pageable pageable) {
		return repository.findByStoreNameContaining(search,pageable);
	}
	

}

