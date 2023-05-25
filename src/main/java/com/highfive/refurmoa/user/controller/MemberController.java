package com.highfive.refurmoa.user.controller;

import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.highfive.refurmoa.user.dto.request.LoginDTO;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberServiceImpl memberServiceImpl;

    @PostMapping("signup")
    public int insertMember(@RequestBody SignupRequestDto signupRequestDto) {
        return memberServiceImpl.insertMember(signupRequestDto);
    }
    @PostMapping("/login")
	public int loginUser(@RequestBody LoginDTO login ) {
		return memberServiceImpl.login(login);
	}
}
