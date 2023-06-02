package com.highfive.refurmoa.post.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentRequestDTO {
    private String member_id;
    private int page;
    private int size;
}
