package com.highfive.refurmoa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.highfive.refurmoa.entity.Member;


public interface MemberRepository extends JpaRepository<Member, String> {
	
//	@Query(value="select count(*) from member where member_id = :memberId", nativeQuery=true)
//	int countMember(@Param("memberId") String memberId);
	
	long countByMemberId(String memberId);
}
