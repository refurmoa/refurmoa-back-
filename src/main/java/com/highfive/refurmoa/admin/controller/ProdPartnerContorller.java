package com.highfive.refurmoa.admin.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.admin.service.ProdPartnerServiceImpl;
import com.highfive.refurmoa.entity.ProdPartner;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProdPartnerContorller {
	
	private final ProdPartnerServiceImpl ProdPartnerServiceImpl;
	
	
	@GetMapping("/admin/partner/detail")
	 public PartnerDTO findPartner(@RequestParam(value="com_num") int search) {
      return ProdPartnerServiceImpl.findPartner(search);
	}
	
	@GetMapping("/admin/partner/detail/change")
	 public PartnerDTO changState(@RequestParam(value="com_num") int com_num) {
     return ProdPartnerServiceImpl.changState(com_num);
	}
	@GetMapping("/partner/search")
	 public Page<PartnerDTO> partnerSearch(@RequestParam(value="search") String search, Pageable pageable) {
       return ProdPartnerServiceImpl.partnerSearch(search,pageable);
   }
	@GetMapping("/admin/partner")
	 public Page<PartnerDTO> Adminpartner(@RequestParam(value="search") String search, Pageable pageable) {
      return ProdPartnerServiceImpl.Adminpartner(search,pageable);
  }
	@PostMapping("/admin/partner/update")
	public int updatePartner(@RequestBody ProdPartner partner) {
		return ProdPartnerServiceImpl.updatePartner(partner);
	}
}
