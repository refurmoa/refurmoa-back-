package com.highfive.refurmoa.admin.service;



import java.util.List;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;

public interface ProdPartnerService {

	public List<PartnerDTO> partnerSearch(String search);//제휴회사 검색
}
