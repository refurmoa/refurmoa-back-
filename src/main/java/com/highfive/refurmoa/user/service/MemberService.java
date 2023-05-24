package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.user.dto.request.SignupRequestDto;

public interface MemberService {
    public int insertMember(SignupRequestDto signupRequestDto);
}
