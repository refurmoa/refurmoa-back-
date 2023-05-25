package com.highfive.refurmoa.user.service;
import org.springframework.stereotype.Service;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.repository.MemberRepository;
import java.util.Date;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository repository;
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
    }

    @Override // 로그인
    public int login(String member_id, String password) {
        Member member = repository.findByMemberIdAndPassword(member_id, password);
        if (member != null) return 1;
        else return 0;
    }

    @Override // ID 찾기
    public String findID(String name, String phone) {
        Member member = repository.findByNameAndPhone(name, phone);
        if (member != null) return member.getMemberId();
        else return null;
    }

    @Override // PW 찾기
    public String findPW(String member_id, String name, String phone) {
        Member member = repository.findByMemberIdAndNameAndPhone(member_id, name, phone);
        if (member != null) return member.getPassword();
        else return null;
    }

    @Override // 회원가입
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

    @Override // ID 중복 검사
    public long countMemberId(String memberId) {
        return repository.countByMemberId(memberId);
    }

}