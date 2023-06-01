package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import com.highfive.refurmoa.cs.dto.request.InquiryReplyDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryDetailDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryListDTO;

public interface InquiryService {
	
	//목록 조회
	public Page<InquiryListDTO> inquiryList(String id, Pageable pageable);
	//목록 상세
	public InquiryDetailDTO inquiryDetail(int num);
	//목록 삭제
	public int inquiryDelete(int num);
	//이름 조회
	public String findName(String id);
	//답변 등록
	public int writeReply(InquiryReplyDTO answer);
	
}
