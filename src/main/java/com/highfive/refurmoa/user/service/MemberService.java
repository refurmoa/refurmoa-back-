package com.highfive.refurmoa.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.post.dto.reponse.MyListDTO;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;
import com.highfive.refurmoa.user.DTO.reponse.MemberInfoDTO;
import com.highfive.refurmoa.user.DTO.reponse.MembershipDTO;

public interface MemberService {

    public int login(String member_id, String password); // 로그인
    public String findID(String name, String phone); // ID 찾기
    public String findPW(String member_id, String name, String phone); // PW 찾기
    public void insertMember(Member member); // 회원가입
    public long countMemberId(String memberId); // ID 중복 검사

    public String userLocationInfo(String memberId, boolean acceptLocation);	//	회원주소검색
//    public List<AdminUserListResponseDTO> listAdminMember();	// 관리자 페이지 회원 목록 조회
    public Slice<AdminUserListResponseDTO> listAdminMember(Pageable pageable);	// 관리자 페이지 회원 목록 조회
    public List<AdminUserListResponseDTO> searchAdminMember(String memberId);	// admin 회원 검색
    public MemberInfoDTO memberInfo(String id);//마이페이지 정보 조회 
    public MembershipDTO membership(String id);//마이페이지 멤버십 조회 
    public List<MyListDTO> bookmarkData(String id,String search);
    
}
