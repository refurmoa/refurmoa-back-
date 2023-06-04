package com.highfive.refurmoa.user.controller;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.service.UserServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserServiceImpl userService;

    // 회원 정보 조회
    @RequestMapping("/info")
    public Member getMemberInfo(@RequestBody Member member) {
        return userService.getMemberInfo(member.getMemberId());
    }

    // 회원 탈퇴
    @RequestMapping("/delete")
    public void deleteMember(@RequestBody Member vo){
        userService.deleteMember(vo.getMemberId());
    }

    // 회원 정보 수정
    @RequestMapping("/update")
    public void updateMember(@RequestBody Member member) {
        userService.updateMember(member);
    }
}
