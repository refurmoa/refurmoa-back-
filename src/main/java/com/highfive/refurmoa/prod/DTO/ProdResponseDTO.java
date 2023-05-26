package com.highfive.refurmoa.prod.DTO;

import com.highfive.refurmoa.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdResponseDTO {

	private Product prod;
	private String com_name;
	private int com_num;
}
