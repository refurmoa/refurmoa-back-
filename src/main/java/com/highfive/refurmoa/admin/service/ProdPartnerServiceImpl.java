package com.highfive.refurmoa.admin.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.prod.repository.ProductRepository;
@Service
public class ProdPartnerServiceImpl implements ProdPartnerService {
	
	 private ProductRepository repository;
	 private ProdPartnerRepository partnerRepository;

	 public ProdPartnerServiceImpl(ProductRepository repository,ProdPartnerRepository partnerRepository ) {
	        this.repository = repository;
	        this.partnerRepository=partnerRepository;
	 }
	 
	@Override
	public Page<PartnerDTO> partnerSearch(String search, Pageable pageable) {
		List<ProdPartner> tmp=partnerRepository.findByComNameContaining(search);
		List<PartnerDTO> list = new ArrayList<PartnerDTO>();
		for(int i=0;i<tmp.size();i++) {
			int cnt=repository.countCom(tmp.get(i).getComNum());
			PartnerDTO temp=new PartnerDTO(tmp.get(i),cnt);
			list.add(temp);
		}
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());
		Page<PartnerDTO> Page = new PageImpl<>(list.subList(start, end), pageRequest, list.size());
		return Page;
	}
	@Override
	public Page<PartnerDTO> Adminpartner(String search, Pageable pageable) {
		List<ProdPartner> tmp=partnerRepository.findComAdmin(search);
		List<PartnerDTO> list = new ArrayList<PartnerDTO>();
		for(int i=0;i<tmp.size();i++) {
			int cnt=repository.countCom(tmp.get(i).getComNum());
			PartnerDTO temp=new PartnerDTO(tmp.get(i),cnt);
			list.add(temp);
		}
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());
		Page<PartnerDTO> Page = new PageImpl<>(list.subList(start, end), pageRequest, list.size());
		return Page;
	}
}
