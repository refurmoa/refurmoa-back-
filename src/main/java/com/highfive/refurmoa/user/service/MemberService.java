package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.user.dto.request.SignupRequestDto;
import com.highfive.refurmoa.user.entity.Member;

public interface MemberService {
    public int insertMember(SignupRequestDto signupRequestDto);
}
