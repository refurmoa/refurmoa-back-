package com.highfive.refurmoa.user.repository;

import com.highfive.refurmoa.user.entity.MemberEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<MemberEntity, String> {
}
