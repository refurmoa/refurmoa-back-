package com.highfive.refurmoa.user.DTO.reponse;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class mileDTO {
	private int amount;
	private List<historyDTO> history;

}
