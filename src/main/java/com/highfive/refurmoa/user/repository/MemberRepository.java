package com.highfive.refurmoa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByMemberIdAndPassword(String member_id, String password);
	
	Member findByMemberId(String member_id);
	
	String findByName(String member_id);

	Member findByNameAndPhone(String name, String phone);

	Member findByMemberIdAndNameAndPhone(String member_id, String name, String phone);

	long countByMemberId(String memberId);	    // ID 중복 검사
	  
	List<Member> findAllByMemberId(String memberId);	// 회원정보 불러오기
	
	Member findAllByMemberIdAndAcceptLocation(String memberId, boolean acceptLocation);	//	회원주소검색

}
