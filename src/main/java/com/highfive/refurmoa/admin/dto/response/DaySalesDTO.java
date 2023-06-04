package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@Getter
@Setter
public class DaySalesDTO {
    private String date;
    private Long auction;
    private Long direct;
    private Long cancel;
    private Long count;

    public DaySalesDTO(LocalDate date, Long auction, Long direct, Long cancel, Long count) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd");
        this.date = date.format(formatter);
        this.auction = auction;
        this.direct = direct;
        this.cancel = cancel;
        this.count = count;
    }

}
