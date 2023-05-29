package com.highfive.refurmoa.user.service;
import java.util.List;

import com.highfive.refurmoa.entity.Member;


public interface MemberService {

    public long countMemberId(String memberId);
    public List<Member> listMember(String memberId);
    public void deleteMember(String memberId);
    public Member updateMember(Member member);
}
	