package com.highfive.refurmoa.prod.DTO;

import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ProdListDTO {
	
	private Product prod;
	private int sell_status;
	
}
