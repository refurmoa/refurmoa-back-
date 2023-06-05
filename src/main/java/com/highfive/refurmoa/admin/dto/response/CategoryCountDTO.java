package com.highfive.refurmoa.admin.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryCountDTO {
    private String id;
    private String label;
    private Long value;

    public CategoryCountDTO(String id, Long value) {
        this.id = id;
        this.label = id;
        this.value = value;
    }
}
