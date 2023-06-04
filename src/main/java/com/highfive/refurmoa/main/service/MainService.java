package com.highfive.refurmoa.main.service;

import java.util.List;

import com.highfive.refurmoa.entity.Banner;
import com.highfive.refurmoa.post.dto.reponse.MainListDTO;


public interface MainService {
	
	public List<List<MainListDTO>> mainList();
	public List<Banner> bannerList();
	public List<Banner> bannerAdList();
}
