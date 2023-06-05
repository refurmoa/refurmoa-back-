package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class MonthSalesDTO {
    private String date;
    private Long sales;

    public MonthSalesDTO(YearMonth date, Long sales) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yy-MM");
        this.date = date.format(formatter);
        this.sales = sales;
    }
}
