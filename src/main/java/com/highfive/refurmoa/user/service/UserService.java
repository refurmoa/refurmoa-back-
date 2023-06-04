package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.entity.Member;

public interface UserService {

    public Member getMemberInfo(String memberId); // 회원 정보 조회
    public void deleteMember(String memberId); // 회원 탈퇴
    public Member updateMember(Member member); // 회원 정보 수정
}
