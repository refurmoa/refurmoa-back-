package com.highfive.refurmoa.user.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChangeToConfirmRequestDTO {
    private String member_id;
    private int product_code;
}
