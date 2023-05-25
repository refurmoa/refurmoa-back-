package com.highfive.refurmoa.user.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.highfive.refurmoa.user.dto.request.LoginDTO;
import com.highfive.refurmoa.entity.Member;
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
      
//  일반 쿼리문
//	@RequestMapping("/signup/distinct")
//	public int countMember(@RequestBody Member vo) {
//		System.out.println(vo);
//		return memberServiceImpl.countMember(vo.getMemberId());
//	}
	
//	jpa repository 활용
	  @PostMapping("/signup/distinct")
    public long countMemberId(@RequestBody Member vo) {
      System.out.println(vo);
      return memberServiceImpl.countMemberId(vo.getMemberId());
    }
}
