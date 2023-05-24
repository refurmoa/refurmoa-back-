package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.entity.Member;
import com.highfive.refurmoa.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.util.Date;

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
        Member member = new Member(MEMBER_ID, PASSWORD, NAME, PHONE, EMAIL, ADDRESS, DETAIL_ADDRESS, BIRTH, GRADE, MILE, ACCEPT_LOCATION, ACCEPT_ALARM);
        repository.save(member);
        return 1;
    }
}