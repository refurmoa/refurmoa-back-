package com.highfive.refurmoa.user.service;
import java.util.List;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.dto.request.LoginDTO;
import com.highfive.refurmoa.user.dto.request.SignupRequestDto;

public interface MemberService {
    public int insertMember(SignupRequestDto signupRequestDto);
    public int login(LoginDTO login);
    public long countMemberId(String memberId);
    public List<Member> listMember(String memberId);
    public void deleteMember(String memberId);
    public Member updateMember(String memberId, Member member);
}
	