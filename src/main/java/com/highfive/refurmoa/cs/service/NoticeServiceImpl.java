package com.highfive.refurmoa.cs.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.cs.repository.NoticeRepository;
import com.highfive.refurmoa.entity.NoticeBoard;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeServiceImpl implements NoticeService {
	
	private final NoticeRepository repository;
	
	// 공지사항 등록
	@Override
	public NoticeBoard insertNotice(NoticeBoard noticeBoard) {
		return repository.save(noticeBoard);
	}
	
	// 공지사항 목록 조회
	@Override
	public Page<NoticeBoard> listNoticeBoard(Pageable pageable){
		return repository.findAll(pageable);
	}
	
}
