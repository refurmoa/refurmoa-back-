package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.entity.NoticeBoard;

public interface NoticeService {
	
	public NoticeBoard insertNotice(NoticeBoard noticeBoard);	// 공지사항 등록하기
	public Page<NoticeBoard> listNoticeBoard(Pageable pageable);	// 공지사항 목록 조회하기
	public NoticeBoard updateNotice(NoticeBoard noticeBoard); // 공지사항 수정하기
	public void deleteNotice(int notiNum); // 공지사항 삭제하기
}
