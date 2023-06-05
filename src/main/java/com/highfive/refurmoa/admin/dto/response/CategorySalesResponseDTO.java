package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CategorySalesResponseDTO {
    List<CategoryCountDTO> category_countList;

    public CategorySalesResponseDTO(List<CategoryCountDTO> data) {
        this.category_countList = data;
    }
}
