package com.highfive.refurmoa.user.DTO.reponse;

import com.highfive.refurmoa.entity.Delivery;
import com.highfive.refurmoa.entity.Payment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentListResponseDTO {
    private int board_num;
    private String pay_num;
    private int sell_type;
    private String main_image;
    private String prod_com;
    private String prod_name;
    private int prod_price;
    private int pay_price;
    private int pay_state;
    private boolean pay_cancel;
    private int product_code;

    public PaymentListResponseDTO(Payment payment, Delivery delivery) {
        this.board_num = payment.getBoard().getBoardNum();
        this.pay_num = payment.getPayNum();
        this.sell_type = payment.getBoard().getSellType();
        this.main_image = payment.getBoard().getProduct().getMainImage();
        this.product_code = payment.getProduct().getProductCode();
        this.prod_com = payment.getBoard().getProduct().getProdCom();
        this.prod_name = payment.getBoard().getProduct().getProdName();
        this.prod_price = payment.getBoard().getCurPrice();
        this.pay_price = payment.getPayPrice();
        System.out.println(delivery);
        if (delivery == null) {
            this.pay_state = 1;
        } else {
            if (payment.getBoard().getProduct().getProdState() == 3) {
                this.pay_state = 2;
            } else if (payment.getBoard().getProduct().getProdState() == 4) {
                this.pay_state = 3;
            } else if (payment.getBoard().getProduct().getProdState() == 5) {
                this.pay_state = 4;
            }
        }
        this.pay_cancel = payment.isPayCancel();
    }
}
