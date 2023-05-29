package com.highfive.refurmoa.admin.service;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;

public interface ProdPartnerService {

	public Page<PartnerDTO> partnerSearch(String search, Pageable pageable);//제휴회사 검색
	public Page<PartnerDTO> Adminpartner(String search, Pageable pageable);
}
