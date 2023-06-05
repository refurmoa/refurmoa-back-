package com.highfive.refurmoa.pay.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.pay.dto.response.DeliResponseDTO;
import com.highfive.refurmoa.pay.service.DeliServiceImpl;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class AdminOrderController {
	
	
	private final DeliServiceImpl deliServiceImpl;
	 //주문 조회
    @GetMapping("/admin/order")
	 public Page<DeliResponseDTO> adminOrder(@RequestParam(value="search") String search,Pageable pageable) {
    	System.out.println(search);
    	System.out.println(search);
    	System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);System.out.println(search);
		return deliServiceImpl.adminOrder(search,pageable);
	}
}
