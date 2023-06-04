package com.highfive.refurmoa.user.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.post.dto.reponse.MyListDTO;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;
import com.highfive.refurmoa.user.DTO.reponse.MemberInfoDTO;
import com.highfive.refurmoa.user.DTO.reponse.MembershipDTO;
import com.highfive.refurmoa.user.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    // 로그인
    @PostMapping("/login")
    public int loginUser(@RequestBody Member member) {
        return memberServiceImpl.login(member.getMemberId(), member.getPassword());
    }

    // ID 찾기
    @PostMapping("/findid")
    public String findID(@RequestBody Member member) {
        return memberServiceImpl.findID(member.getName(), member.getPhone());
    }

    // PW 찾기
    @PostMapping("/findpw")
    public String findPW(@RequestBody Member member) {
        return memberServiceImpl.findPW(member.getMemberId(), member.getName(), member.getPhone());
    }

    // 회원가입
    @PostMapping("/signup")
    public void insertMember(@RequestBody Member member) {
        memberServiceImpl.insertMember(member);
    }

    // ID 중복 검사
    @PostMapping("/signup/distinct")
    public long countMemberId(@RequestBody Member vo) {
        return memberServiceImpl.countMemberId(vo.getMemberId());
    }
    


 	//	회원주소검색
 	@RequestMapping("/cs/as/user/addr")
 	public String userLocationInfo(@RequestBody Member vo){
 		return memberServiceImpl.userLocationInfo(vo.getMemberId(), vo.isAcceptLocation());
 	}
 	
// 	// 관리자 페이지 회원관리 회원목록 불러오기
// 	@RequestMapping("/admin/user")
// 	public List<AdminUserListResponseDTO> listAdminMember(){
// 		return (List<AdminUserListResponseDTO>)memberServiceImpl.listAdminMember();
// 	}
 	
 	// 관리자 페이지 회원관리 회원목록 불러오기
  	@RequestMapping("/admin/user")
  	public Slice<AdminUserListResponseDTO> listAdminMember(Pageable pageable){
  		return (Slice<AdminUserListResponseDTO>)memberServiceImpl.listAdminMember(pageable);
  	}
 	
 	// admin 회원 검색
 	@RequestMapping("/admin/user/search")
 	public List<AdminUserListResponseDTO> searchAdminMember(@RequestBody Member vo){
 		return (List<AdminUserListResponseDTO>)memberServiceImpl.searchAdminMember(vo.getMemberId());
 	}
 	
 	
 	@PostMapping("/mypage/memberinfo")
 	public MemberInfoDTO memberInfo(@RequestParam("id") String id) {
 		return memberServiceImpl.memberInfo(id);
 	}
 	@PostMapping("/mypage/membership")
 	public MembershipDTO membership (@RequestParam("id") String id) {
 		return memberServiceImpl.membership (id);
 	}
 	
 	@PostMapping("/mypage/bookmark")
 	public List<MyListDTO> bookmark (@RequestParam("id") String id,@RequestParam("search") String search) {
 		return memberServiceImpl.bookmarkData(id,search);
 	}
 	@PostMapping("/mypage/bookmark/search")
 	public List<MyListDTO> bookmarkserach (@RequestParam("id") String id,@RequestParam("search") String search) {
 		return memberServiceImpl.bookmarkData(id,search);
 	}
 	
 
}