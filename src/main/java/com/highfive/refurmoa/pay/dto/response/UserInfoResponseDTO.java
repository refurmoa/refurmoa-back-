package com.highfive.refurmoa.pay.dto.response;

import com.highfive.refurmoa.entity.Member;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfoResponseDTO {
    private String name;
    private String phone;
    private String email;
    private String address;
    private String detail_address;
    private int mile;

    public UserInfoResponseDTO(Member member) {
        this.name = member.getName();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.address = member.getAddress();
        this.detail_address = member.getDetailAddress();
        this.mile = member.getMile();
    }
}
