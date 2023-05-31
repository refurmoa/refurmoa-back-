package com.highfive.refurmoa.cs.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.cs.dto.response.InquiryListDTO;
import com.highfive.refurmoa.cs.service.InquiryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class InquiryController {
	 private final InquiryServiceImpl inquiryServiceImpl;
	 
	 //목록 조회
	 @GetMapping("/cs/inquiry")
	 public Page<InquiryListDTO> inquiryList(@RequestParam(value="id") String id, Pageable pageable) {
	
       return inquiryServiceImpl.inquiryList(id,pageable);
	 }
	 
}
