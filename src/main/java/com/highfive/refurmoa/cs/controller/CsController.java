package com.highfive.refurmoa.cs.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.cs.service.CsServiceImpl;
import com.highfive.refurmoa.entity.AsStore;

@RestController
public class CsController {
	
private CsServiceImpl csServiceImpl;

	public CsController(CsServiceImpl csServiceImpl) {
		super();
		this.csServiceImpl = csServiceImpl;
	}
	
	@RequestMapping("/cs/as")
	public List<AsStore> listAsStore(){
		return (List<AsStore>)csServiceImpl.listAsStore();
	}
	
	@RequestMapping("/cs/as/admin/write")
	public AsStore insertAsStore(@RequestBody AsStore asStore) {
		return csServiceImpl.insertAsStore(asStore);
	}
	
	@RequestMapping("/cs/as/admin/update")
	public AsStore updateAsStore(@RequestBody AsStore asStore) {
		return csServiceImpl.updateAsStore(asStore);
	}
	
	@RequestMapping("/cs/as/admin/delete")
	public void deleteAsStore(@RequestBody AsStore vo) {
		csServiceImpl.deleteAsStore(vo.getStoreNum());
	}
	
	@RequestMapping("/cs/as/search/city")
	public List<AsStore> searchAsStoreCity(@RequestBody AsStore vo) {
		return (List<AsStore>)csServiceImpl.searchAsStoreCity(vo.getStoreAddr());
	}
	
	@RequestMapping("/cs/as/search/text")
	public List<AsStore> searchAsStoreText(@RequestBody AsStore vo) {
		return (List<AsStore>)csServiceImpl.searchAsStoreText(vo.getStoreName());
	}

}
