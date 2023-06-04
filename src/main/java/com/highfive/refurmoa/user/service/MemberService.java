package com.highfive.refurmoa.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;
import com.highfive.refurmoa.user.DTO.reponse.MemberInfoDTO;

public interface MemberService {

    public int login(String member_id, String password); // 로그인
    public String findID(String name, String phone); // ID 찾기
    public String findPW(String member_id, String name, String phone); // PW 찾기
    public void insertMember(Member member); // 회원가입
    public long countMemberId(String memberId); // ID 중복 검사
    public List<Member> listMember(String memberId);	// 회원정보 불러오기
    public void deleteMember(String memberId);	// 회원탈퇴
    public Member updateMember(Member member);	// 회원정보 수정
    public String userLocationInfo(String memberId, boolean acceptLocation);	//	회원주소검색
//    public List<AdminUserListResponseDTO> listAdminMember();	// 관리자 페이지 회원 목록 조회
    public Slice<AdminUserListResponseDTO> listAdminMember(Pageable pageable);	// 관리자 페이지 회원 목록 조회
    public List<AdminUserListResponseDTO> searchAdminMember(String memberId);	// admin 회원 검색
    public MemberInfoDTO memberInfo(String id);//마이페이지 정보 조회 
    
}
