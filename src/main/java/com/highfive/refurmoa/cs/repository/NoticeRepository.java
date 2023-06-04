package com.highfive.refurmoa.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.NoticeBoard;

public interface NoticeRepository extends JpaRepository<NoticeBoard, Integer> {
	
	Page<NoticeBoard> findAll(Pageable pageable);	// 공지사항 목록 조회

	
}
