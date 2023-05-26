package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.entity.Member;

public interface MemberService {

    public int login(String member_id, String password); // 로그인
    public String findID(String name, String phone); // ID 찾기
    public String findPW(String member_id, String name, String phone); // PW 찾기
    public int insertMember(Member member); // 회원가입
    public long countMemberId(String memberId); // ID 중복 검사

}