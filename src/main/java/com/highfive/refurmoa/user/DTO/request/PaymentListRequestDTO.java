package com.highfive.refurmoa.user.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentListRequestDTO {
    private String member_id;
    private int page;
    private int size;
}
