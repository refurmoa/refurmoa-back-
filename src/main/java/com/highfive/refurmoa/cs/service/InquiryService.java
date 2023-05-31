package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.cs.dto.response.InquiryDetailDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryListDTO;

public interface InquiryService {
	
	//목록 조회
	public Page<InquiryListDTO> inquiryList(String id, Pageable pageable);
	//목록 상세
	public InquiryDetailDTO inquiryDetail(int num);
}
