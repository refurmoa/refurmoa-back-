package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AdminCountResponseDTO {
    private Long yet;
    private Long ingauction;
    private Long ingdirect;
    private Long waitpay;
    private Long prepare;
    private Long shipping;
    private Long completed;
    private Long productInquiry;
    private Long oneononeInquiry;
    private Long partnership;

    public AdminCountResponseDTO(Long yet, Long ingauction, Long ingdirect, Long waitpay, Long prepare, Long shipping,
                                 Long completed, Long productInquiry, Long oneononeInquiry, Long partnership) {
        this.yet = yet;
        this.ingauction = ingauction;
        this.ingdirect = ingdirect;
        this.waitpay = waitpay;
        this.prepare = prepare;
        this.shipping = shipping;
        this.completed = completed;
        this.productInquiry = productInquiry;
        this.oneononeInquiry = oneononeInquiry;
        this.partnership = partnership;
    }
}
