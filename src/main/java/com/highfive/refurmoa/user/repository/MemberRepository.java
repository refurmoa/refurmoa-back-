package com.highfive.refurmoa.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {
	
	@Query(value="select member_id from member where member_id=:id",nativeQuery=true)
	String getId(@Param("id")String id);
	
	@Query(value="select password from member where member_id=:id",nativeQuery=true)
	String getPw(@Param("id") String id);
  
	long countByMemberId(String memberId);
  
	List<Member> findByMemberId(String memberId);
	

}
