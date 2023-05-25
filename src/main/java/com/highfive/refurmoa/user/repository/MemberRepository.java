package com.highfive.refurmoa.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.highfive.refurmoa.entity.Member;

public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByMemberIdAndPassword(String member_id, String password);

	Member findByNameAndPhone(String name, String phone);

	Member findByMemberIdAndNameAndPhone(String member_id, String name, String phone);

	long countByMemberId(String memberId);

}
