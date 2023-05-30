package com.highfive.refurmoa.prod.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdSearchDTO {
	private String searchword;
    private String category;
    private String sellstatus;
}
