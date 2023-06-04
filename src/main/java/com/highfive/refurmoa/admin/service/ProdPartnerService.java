package com.highfive.refurmoa.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;

public interface ProdPartnerService {
	public PartnerDTO changState(int com_num);
	public Page<PartnerDTO> partnerSearch(String search, Pageable pageable);
	public Page<PartnerDTO> Adminpartner(String search, Pageable pageable);
	public int insertPartner(ProdPartner partner);
	public int updatePartner(ProdPartner partner);
	public PartnerDTO findPartner(int search);
	public Page<ProdListDTO>partnerProduct(int num, String search,Pageable pageable);
}
