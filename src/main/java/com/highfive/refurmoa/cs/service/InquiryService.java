package com.highfive.refurmoa.cs.service;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.cs.dto.request.InquiryReplyDTO;
import com.highfive.refurmoa.cs.dto.request.InquiryWriteDTO;
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
	//문의 등록
	public int writeInquiry( MultipartFile inqImg, InquiryWriteDTO write) throws IOException;
	
}
