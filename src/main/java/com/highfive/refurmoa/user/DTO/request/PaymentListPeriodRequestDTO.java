package com.highfive.refurmoa.user.DTO.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentListPeriodRequestDTO {
    private String member_id;
    private String date_start;
    private String date_end;
    private int page;
    private int size;
}
