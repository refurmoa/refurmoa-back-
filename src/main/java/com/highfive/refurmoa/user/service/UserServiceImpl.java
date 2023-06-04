package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private MemberRepository memberRepository;
    public UserServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    // 회원 정보 조회
    @Override
    public Member getMemberInfo(String memberId) {
        return memberRepository.findByMemberId(memberId);
    }

    // 회원 탈퇴
    @Override
    public void deleteMember(String memberId) {
        memberRepository.deleteById(memberId);
    }

    // 회원 정보 수정
    @Override
    public Member updateMember(Member member) {
        return memberRepository.save(member);
    }

}
