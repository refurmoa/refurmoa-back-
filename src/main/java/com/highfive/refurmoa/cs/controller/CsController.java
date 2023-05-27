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
	
	@RequestMapping("/cs/as/admin/update/{storeNum}")
	public AsStore updateAsStore(@PathVariable int storeNum, @RequestBody AsStore asStore) {
		return csServiceImpl.updateAsStore(storeNum, asStore);
	}
	
	@RequestMapping("/cs/as/admin/delete")
	public void deleteAsStore(@RequestBody AsStore vo) {
		csServiceImpl.deleteAsStore(vo.getStoreNum());
	}
	
	@RequestMapping("/cs/as/search")
	public List<AsStore> searchAsStore(@RequestBody AsStore vo) {
		return (List<AsStore>)csServiceImpl.searchAsStore(vo.getStoreAddr());
	}
	
	@RequestMapping("/cs/as/search2")
	public List<AsStore> searchAsStore2(@RequestBody AsStore vo) {
		return (List<AsStore>)csServiceImpl.searchAsStore2(vo.getStoreName());
	}

}
