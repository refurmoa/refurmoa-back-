package com.highfive.refurmoa.prodpartner.service;



import java.util.List;

import com.highfive.refurmoa.prodpartner.DTO.PartnerDTO;

public interface ProdPartnerService {
	public List<PartnerDTO> partnerSearch(String search);
}
