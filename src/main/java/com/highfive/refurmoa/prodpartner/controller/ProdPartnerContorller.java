package com.highfive.refurmoa.prodpartner.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.prodpartner.DTO.PartnerDTO;
import com.highfive.refurmoa.prodpartner.service.ProdPartnerServiceImpl;

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
