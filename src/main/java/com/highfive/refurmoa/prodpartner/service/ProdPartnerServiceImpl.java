package com.highfive.refurmoa.prodpartner.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.prod.repository.ProductRepository;
import com.highfive.refurmoa.prodpartner.DTO.PartnerDTO;
import com.highfive.refurmoa.prodpartner.repository.ProdPartnerRepository;
@Service
public class ProdPartnerServiceImpl implements ProdPartnerService {
	
	 private ProductRepository repository;
	 private ProdPartnerRepository partnerRepository;

	 public ProdPartnerServiceImpl(ProductRepository repository,ProdPartnerRepository partnerRepository ) {
	        this.repository = repository;
	        this.partnerRepository=partnerRepository;
	 }
	 
	@Override
	public List<PartnerDTO> partnerSearch(String search) {
		List<ProdPartner> tmp=partnerRepository.findByComNameContaining(search);
		List<PartnerDTO> list = new ArrayList<PartnerDTO>();
		for(int i=0;i<tmp.size();i++) {
			int cnt=repository.countCom(tmp.get(i).getComNum());
			PartnerDTO temp=new PartnerDTO(tmp.get(i),cnt);
			list.add(temp);
		}
		return list;
	}
}
