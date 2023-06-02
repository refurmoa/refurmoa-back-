package com.highfive.refurmoa.post.dto.reponse;

import com.highfive.refurmoa.entity.Payment;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {
    private int board_num;
    private int pay_num;
    private int sell_type;
    private String main_image;
    private String prod_com;
    private String prod_name;
    private int prod_price;
    private int pay_price;
    private int pay_state;
    private boolean pay_cancel;

    public PaymentResponseDTO(Payment payment) {
        this.board_num = payment.getBoardNum().getBoardNum();
        this.pay_num = payment.getPayNum();
        this.sell_type = payment.getBoardNum().getSellType();
        this.main_image = payment.getBoardNum().getProduct().getMainImage();
        this.prod_com = payment.getBoardNum().getProduct().getProdCom();
        this.prod_name = payment.getBoardNum().getProduct().getProdName();
        this.prod_price = payment.getProdPrice();
        this.pay_price = payment.getPayPrice();
        if ((payment.getBoardNum().getSellType() == 1) ||
                (payment.getBoardNum().getSellType() == 3)) {

        }
        this.pay_state = payment.getPayNum();
        this.pay_cancel = payment.isPayCancel();

    }
}
