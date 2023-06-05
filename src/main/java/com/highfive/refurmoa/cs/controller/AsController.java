package com.highfive.refurmoa.cs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.cs.service.AsServiceImpl;
import com.highfive.refurmoa.entity.AsStore;

@RestController
public class AsController {
	
private AsServiceImpl asServiceImpl;

	public AsController(AsServiceImpl asServiceImpl) {
		super();
		this.asServiceImpl = asServiceImpl;
	}
	
//	as 매장 조회
	@RequestMapping("/cs/as")
	public Page<AsStore> listAsStore(Pageable pageable){
		return asServiceImpl.listAsStore(pageable);
	}
	
//	as 매장 등록
	@RequestMapping("/cs/as/admin/write")
	public AsStore insertAsStore(@RequestBody AsStore asStore) {
		return asServiceImpl.insertAsStore(asStore);
	}
	
//	as 매장 수정
	@RequestMapping("/cs/as/admin/update")
	public AsStore updateAsStore(@RequestBody AsStore asStore) {
		return asServiceImpl.updateAsStore(asStore);
	}
	
//	as 매장 삭제
	@RequestMapping("/cs/as/admin/delete")
	public void deleteAsStore(@RequestBody AsStore vo) {
		asServiceImpl.deleteAsStore(vo.getStoreNum());
	}
	
//	as 매장 지역 검색
	@GetMapping("/cs/as/search/city")
	public Page<AsStore> searchAsStoreCity(@RequestParam(value="storeAddr") String addr,@RequestParam(value="storeDetail") String detail,Pageable pageable) {
		return asServiceImpl.searchAsStoreCity(addr,detail,pageable);
	}
	
//	as 매장명 검색
	@GetMapping("/cs/as/search/text")
	public Page<AsStore> searchAsStoreText(@RequestParam(value="search") String search,Pageable pageable) {
		return asServiceImpl.searchAsStoreText(search,pageable);
	}
	
}
