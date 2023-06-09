package com.highfive.refurmoa.cs.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.cs.dto.response.NoticeResponseDTO;
import com.highfive.refurmoa.cs.service.NoticeServiceImpl;
import com.highfive.refurmoa.entity.NoticeBoard;

@RestController
public class NoticeController {
	
	private NoticeServiceImpl noticeServiceImpl;
	
	public NoticeController(NoticeServiceImpl noticeServiceImpl) {
		super();
		this.noticeServiceImpl = noticeServiceImpl;
	}
	
	@Value("${spring.servlet.multipart.location}")
	String uploadDir;
		
	// 공지사항 file 업로드
	@RequestMapping("notice/file/upload")
	public List<NoticeResponseDTO> upload(@RequestParam MultipartFile[] uploadfiles) throws IOException {
		List<NoticeResponseDTO> list = new ArrayList<>();
		for (MultipartFile file : uploadfiles) {
			if(!file.isEmpty()) {
				File storedFilename = new File(file.getOriginalFilename());
				NoticeResponseDTO entity = new NoticeResponseDTO(file.getOriginalFilename(), 
						   										 storedFilename.toString());
				list.add(entity);
				file.transferTo(storedFilename);	// 업로드
			}
		}
		return list;
	}
	
	// 공지사항 등록하기
	@RequestMapping("/cs/notice/write")
	public NoticeBoard insertNotice(@RequestBody NoticeBoard noticeBoard) {
		return noticeServiceImpl.insertNotice(noticeBoard);
	}
	
	// 공지사항 목록 조회하기
	@RequestMapping("/cs/notice")
	public Page<NoticeBoard> listNoticeBoard(Pageable pageable){
		return noticeServiceImpl.listNoticeBoard(pageable);
	}
	
	// 공지사항 수정하기
	@RequestMapping("/cs/notice/update")
	public void updateNotice(@RequestBody NoticeBoard noticeBoard) {
		noticeServiceImpl.updateNotice(noticeBoard);
	}
	
	// 공지사항 삭제하기
    @RequestMapping("/cs/notice/delete")
    public void deleteNotice(@RequestBody NoticeBoard vo){
    	System.out.println(vo.getNotiNum());
    	noticeServiceImpl.deleteNotice(vo.getNotiNum());
    }

}
