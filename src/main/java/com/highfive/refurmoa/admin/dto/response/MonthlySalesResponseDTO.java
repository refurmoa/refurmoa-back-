package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MonthlySalesResponseDTO {
    private List<MonthSalesDTO> month_sales;

    public MonthlySalesResponseDTO(List<MonthSalesDTO> monthSales) {
        this.month_sales = monthSales;
    }
}
