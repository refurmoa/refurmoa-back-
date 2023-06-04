package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.entity.NoticeBoard;

public interface NoticeService {
	
	public NoticeBoard insertNotice(NoticeBoard noticeBoard);	// 공지사항 등록
	public Page<NoticeBoard> listNoticeBoard(Pageable pageable);	// 공지사항 목록 조회

}
