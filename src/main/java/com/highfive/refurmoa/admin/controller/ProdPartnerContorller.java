package com.highfive.refurmoa.admin.controller;

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.admin.service.ProdPartnerServiceImpl;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class ProdPartnerContorller {
	
	private final ProdPartnerServiceImpl ProdPartnerServiceImpl;
	
	//제휴회사 검색
	@GetMapping("/partner/search")
	 public Page<PartnerDTO> partnerSearch(@RequestParam(value="search") String search, Pageable pageable) {
       return ProdPartnerServiceImpl.partnerSearch(search,pageable);
   }
   @GetMapping("/admin/partner")
	 public Page<PartnerDTO> Adminpartner(@RequestParam(value="search") String search, Pageable pageable) {
      return ProdPartnerServiceImpl.Adminpartner(search,pageable);
  }
}
