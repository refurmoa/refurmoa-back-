package com.highfive.refurmoa.cs.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.cs.dto.request.InquiryReplyDTO;
import com.highfive.refurmoa.cs.dto.request.InquiryWriteDTO;
import com.highfive.refurmoa.cs.dto.response.InquiryDetailDTO;
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
	//목록 상세
	 @GetMapping("/cs/inquiry/detail")
	 public InquiryDetailDTO inquiryList(@RequestParam(value="num") int num) {
       return inquiryServiceImpl.inquiryDetail(num);
	 }
	 //목록 삭제
	 @GetMapping("/cs/inquiry/delete")
	 public int inquiryDelete(@RequestParam(value="num") int num) {
       return inquiryServiceImpl.inquiryDelete(num);
	 }
	 //이름 조회
	 @GetMapping("/cs/inquiry/name")
	 public String findName(@RequestParam(value="id") String id) {
	       return inquiryServiceImpl.findName(id);
	}
	 //답변 작성
	 @PostMapping("/cs/inquiry/detail/reply")
	 public int writeReply(@RequestBody InquiryReplyDTO answer) {
		 return inquiryServiceImpl.writeReply(answer);
	 }
	 
	//답변 작성
	@PostMapping("/cs/inquiry/write")
	public int writeInquiry(@RequestParam(value="inq_img",required = false ) MultipartFile inqImg, InquiryWriteDTO write) throws IOException {
		return inquiryServiceImpl.writeInquiry(inqImg,write);
	}
}

