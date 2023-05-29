package com.highfive.refurmoa.user.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

	@Override
	public long countMemberId(String memberId) {
		return repository.countByMemberId(memberId);
	}
	
	@Override
	public List<Member> listMember(String memberId) {
		return (List<Member>)repository.findByMemberId(memberId);
	}
	
	@Override
	public void deleteMember(String memberId) {
		repository.deleteById(memberId);
	}
	
	@Override
	public Member updateMember(Member member) {
		return repository.save(member);
	}
}
