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
	
	// 공지사항 등록하기
	@Override
	public NoticeBoard insertNotice(NoticeBoard noticeBoard) {
		return repository.save(noticeBoard);
	}
	
	// 공지사항 목록 조회하기
	@Override
	public Page<NoticeBoard> listNoticeBoard(Pageable pageable){
		return repository.findAll(pageable);
	}
	
	// 공지사항 수정하기
    @Override
    public NoticeBoard updateNotice(NoticeBoard noticeBoard) {
        return repository.save(noticeBoard);
    }
    
	// 공지사항 삭제하기
    @Override
    public void deleteNotice(int notiNum) {
        repository.deleteById(notiNum);
    }
	
}
