package com.highfive.refurmoa.admin.service;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.entity.ProdPartner;

public interface ProdPartnerService {
	public Page<PartnerDTO> partnerSearch(String search, Pageable pageable);
	public PartnerDTO changState(int com_num);
	public Page<PartnerDTO> Adminpartner(String search, Pageable pageable);
	public int updatePartner(ProdPartner partner);
	public PartnerDTO findPartner(int search);
}
