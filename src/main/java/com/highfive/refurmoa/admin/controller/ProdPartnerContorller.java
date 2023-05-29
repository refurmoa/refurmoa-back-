package com.highfive.refurmoa.admin.controller;

import java.util.List;

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
	
	@GetMapping("/partner/search")
	 public List<PartnerDTO> partnerSearch(@RequestParam(value="search") String search) {
       return ProdPartnerServiceImpl.partnerSearch(search);
   }
}
