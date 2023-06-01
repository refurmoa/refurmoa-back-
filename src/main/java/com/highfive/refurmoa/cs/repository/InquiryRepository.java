package com.highfive.refurmoa.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Inquiry;
import com.highfive.refurmoa.entity.Member;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>{
	//목록 아이디 조회
	Page<Inquiry> findByMemberId(Member id, Pageable pageable);
	//목록 조회
	Page<Inquiry> findAll(Pageable pageable);
	//목록 상세
	Inquiry findByNum(int num);
	//목록 삭제
	void deleteById(int num);
}
