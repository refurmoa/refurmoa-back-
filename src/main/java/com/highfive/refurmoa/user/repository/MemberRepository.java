package com.highfive.refurmoa.user.repository;

import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, String> {
}
