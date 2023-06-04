package com.highfive.refurmoa.cs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Inquiry;
import com.highfive.refurmoa.entity.Member;
import org.springframework.data.jpa.repository.Query;

public interface InquiryRepository extends JpaRepository<Inquiry, Integer>{
	//목록 아이디 조회
	Page<Inquiry> findByMemberId(Member id, Pageable pageable);
	//목록 조회
	Page<Inquiry> findAll(Pageable pageable);
	//목록 상세
	Inquiry findByNum(int num);
	//목록 삭제
	void deleteById(int num);

	// 어드민 메인페이지
	// 1:1문의 미답변 개수
	@Query("SELECT COUNT(i) FROM Inquiry i " +
			"WHERE i.answerDate IS NULL"
	)
	Long getAdminCountOneononeInquiry();
}
