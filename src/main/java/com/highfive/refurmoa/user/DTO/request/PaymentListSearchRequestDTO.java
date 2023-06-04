package com.highfive.refurmoa.user.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentListSearchRequestDTO {
    private String member_id;
    private String search;
    private int page;
    private int size;
}
