package com.highfive.refurmoa.user.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Date;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDto {
    private String member_id;
    private String password;
    private String name;
    private String phone;
    private String email;
    private String address;
    private String detail_address;
    private Date birth;
    private boolean accept_location;
    private boolean accept_alarm;
}
