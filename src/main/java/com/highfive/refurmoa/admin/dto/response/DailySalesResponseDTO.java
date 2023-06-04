package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DailySalesResponseDTO {
    List<DaySalesDTO> daily_sales;
    public DailySalesResponseDTO(List<DaySalesDTO> daily_sales) {
        this.daily_sales = daily_sales;
    }
}
