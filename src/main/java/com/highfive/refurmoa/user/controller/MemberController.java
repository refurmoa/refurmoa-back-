package com.highfive.refurmoa.user.controller;

import com.highfive.refurmoa.user.service.MemberServiceImpl;
import com.highfive.refurmoa.entity.Member;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
    public int insertMember(@RequestBody Member member) {
        return memberServiceImpl.insertMember(member);
    }

    // ID 중복 검사
    @PostMapping("/signup/distinct")
    public long countMemberId(@RequestBody Member vo) {
        return memberServiceImpl.countMemberId(vo.getMemberId());
    }

}