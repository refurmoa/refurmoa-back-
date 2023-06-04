package com.highfive.refurmoa.post.dto.reponse;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Userlike;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Date;
import java.util.List;

@Getter
@AllArgsConstructor
public class PostResponseDTO {
    private int board_num;
    private String category;
    private int sell_type;
    private String main_image;
    private Date start_date;
    private Date end_date;
    private String prod_com;
    private String prod_name;
    private int prod_state;
    private Long bid_count;
    private int org_price;
    private int cur_price;
    private Integer direct_price;
    private boolean like;

    public PostResponseDTO(Board board, Long bid_count, Userlike user_like) {
        this.board_num = board.getBoardNum();
        this.category = board.getProduct().getCategory();
        this.sell_type = board.getSellType();
        this.main_image = board.getProduct().getMainImage();
        this.start_date = board.getStartDate();
        this.end_date = board.getEndDate();
        this.prod_com = board.getProduct().getProdCom();
        this.prod_name = board.getProduct().getProdName();
        this.prod_state = board.getProduct().getProdState();
        this.bid_count = bid_count;
        this.org_price = board.getProduct().getOrgPrice();
        this.cur_price = board.getCurPrice();
        this.direct_price = board.getDirectPrice();
        if (user_like != null) this.like = true;
        else this.like = false;
    }

}
