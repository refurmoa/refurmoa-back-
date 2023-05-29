package com.highfive.refurmoa.user.service;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.dto.request.LoginDTO;
import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public int insertMember(SignupRequestDto signupRequestDto) {
        String MEMBER_ID = signupRequestDto.getMember_id();
        String PASSWORD = signupRequestDto.getPassword();
        String NAME = signupRequestDto.getName();
        String PHONE = signupRequestDto.getPhone();
        String EMAIL = signupRequestDto.getEmail();
        String ADDRESS = signupRequestDto.getAddress();
        String DETAIL_ADDRESS = signupRequestDto.getDetail_address();
        Date BIRTH = signupRequestDto.getBirth();
        int GRADE = 0;
        int MILE = 0;
        boolean ACCEPT_LOCATION = signupRequestDto.isAccept_location();
        boolean ACCEPT_ALARM = signupRequestDto.isAccept_alarm();
        Member memberEntity = new Member(MEMBER_ID, PASSWORD, NAME, PHONE, EMAIL, ADDRESS, DETAIL_ADDRESS, BIRTH, GRADE, MILE, ACCEPT_LOCATION, ACCEPT_ALARM);
        repository.save(memberEntity);
        return 1;
    }
    @Override
    public int login(LoginDTO login) {
    	if(repository.getId(login.getMember_id())!=null) {
			if(repository.getPw(login.getMember_id()).equals(login.getPassword()))
			{
				return 1;
			}
			else return 0;
		}else {
			return 0;
		}
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
		Member existingMember = repository.findById(member.getMemberId()).get();
		existingMember.setPassword(member.getPassword());
		existingMember.setPhone(member.getPhone());
		existingMember.setEmail(member.getEmail());
		existingMember.setAddress(member.getAddress());
		existingMember.setDetailAddress(member.getDetailAddress());
		return repository.save(existingMember);
	}
}
