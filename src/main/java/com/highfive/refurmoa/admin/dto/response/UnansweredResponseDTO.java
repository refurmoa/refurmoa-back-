package com.highfive.refurmoa.admin.dto.response;

import com.highfive.refurmoa.entity.ProdInquiry;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class UnansweredResponseDTO {
    private int board_num;
    private String title;
    private String date;

    public UnansweredResponseDTO(ProdInquiry prodInquiry) {
        this.board_num = prodInquiry.getBoard().getBoardNum();
        this.title = prodInquiry.getTitle();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.ofInstant(prodInquiry.getDate().toInstant(), ZoneId.systemDefault());
        this.date = localDateTime.format(formatter);
    }
}
