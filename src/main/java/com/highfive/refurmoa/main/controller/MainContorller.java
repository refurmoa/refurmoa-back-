package com.highfive.refurmoa.main.controller;

import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.cs.service.InquiryServiceImpl;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MainContorller {
	private final ProductServiceImpl ProductServiceImpl;
	
	
}
