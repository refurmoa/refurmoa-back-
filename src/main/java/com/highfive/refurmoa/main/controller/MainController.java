package com.highfive.refurmoa.main.controller;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Banner;
import com.highfive.refurmoa.main.service.MainServiceImpl;
import com.highfive.refurmoa.post.dto.reponse.MainListDTO;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
public class MainController {
	
		private final MainServiceImpl MainServiceImpl;
		
		 @GetMapping("/main/item")
		 public List<List<MainListDTO>> mainList() { return MainServiceImpl.mainList(); }
		 @GetMapping("/main/banner")
		 public List<Banner> bannerList() {
	       return MainServiceImpl.bannerList();
		 }
		 @GetMapping("/main/banner/ad")
		 public List<Banner> bannerAdList() {
	       return MainServiceImpl.bannerAdList();
		 }
		
}
