package com.highfive.refurmoa.pay.dto.response;

import com.highfive.refurmoa.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PayInfoResponseDTO {

    private String category_code;
    private int product_code;
    private String prod_com;
    private String prod_name;
    private String main_image;
    private int delivery_price;
    private int price;

    public PayInfoResponseDTO(Board board, int sellType) {
        this.category_code = board.getProduct().getCategoryCode();
        this.product_code = board.getProduct().getProductCode();
        this.prod_com = board.getProduct().getProdCom();
        this.prod_name = board.getProduct().getProdName();
        this.main_image = board.getProduct().getMainImage();
        this.delivery_price = board.getDeliveryPrice();
        if (sellType == 1) this.price = board.getCurPrice();
        else this.price = board.getDirectPrice();

    }
}
