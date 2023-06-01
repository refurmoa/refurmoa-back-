package com.highfive.refurmoa.cs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	public List<AsStore> listAsStore(){
		return (List<AsStore>)asServiceImpl.listAsStore();
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
	@RequestMapping("/cs/as/search/city")
	public List<AsStore> searchAsStoreCity(@RequestBody AsStore vo) {
		return (List<AsStore>)asServiceImpl.searchAsStoreCity(vo.getStoreAddr(), vo.getStoreDetail());
	}
	
//	as 매장명 검색
	@RequestMapping("/cs/as/search/text")
	public List<AsStore> searchAsStoreText(@RequestBody AsStore vo) {
		return (List<AsStore>)asServiceImpl.searchAsStoreText(vo.getStoreName());
	}
	


}
