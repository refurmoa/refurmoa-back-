package com.highfive.refurmoa.user.controller;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.dto.request.LoginDTO;
import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.service.MemberServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("signup")
    public int insertMember(@RequestBody SignupRequestDto signupRequestDto) {
        return memberServiceImpl.insertMember(signupRequestDto);
    }
  
    @PostMapping("/login")
	  public int loginUser(@RequestBody LoginDTO login) {
		return memberServiceImpl.login(login);
    }
      
	@PostMapping("/signup/distinct")
    public long countMemberId(@RequestBody Member vo) {
      return memberServiceImpl.countMemberId(vo.getMemberId());
    }
	  
	@RequestMapping("/user/info")
	public List<Member> listMember(@RequestBody Member vo){
		return (List<Member>)memberServiceImpl.listMember(vo.getMemberId());
	}
	
	@RequestMapping("/user/delete")
	public void deleteMember(@RequestBody Member vo){
		memberServiceImpl.deleteMember(vo.getMemberId());
	}
	
	@RequestMapping("/user/update")
	public void updateMember(@RequestBody Member member){
		memberServiceImpl.updateMember(member);
	}
}
