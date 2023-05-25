package com.highfive.refurmoa.user.service;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.dto.request.LoginDTO;
import com.highfive.refurmoa.user.dto.request.SignupRequestDTO;
import com.highfive.refurmoa.user.repository.MemberRepository;

import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {
    private MemberRepository repository;

    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override
    public int insertMember(SignupRequestDTO signupRequestDTO) {
        String MEMBER_ID = signupRequestDTO.getMember_id();
        String PASSWORD = signupRequestDTO.getPassword();
        String NAME = signupRequestDTO.getName();
        String PHONE = signupRequestDTO.getPhone();
        String EMAIL = signupRequestDTO.getEmail();
        String ADDRESS = signupRequestDTO.getAddress();
        String DETAIL_ADDRESS = signupRequestDTO.getDetail_address();
        Date BIRTH = signupRequestDTO.getBirth();
        int GRADE = 0;
        int MILE = 0;
        boolean ACCEPT_LOCATION = signupRequestDTO.isAccept_location();
        boolean ACCEPT_ALARM = signupRequestDTO.isAccept_alarm();
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
    
//	@Override
//	public int countMember(String memberId) {
//		return repository.countMember(memberId);
//	}
	
	@Override
	public long countMemberId(String memberId) {
		return repository.countByMemberId(memberId);
	}
}
