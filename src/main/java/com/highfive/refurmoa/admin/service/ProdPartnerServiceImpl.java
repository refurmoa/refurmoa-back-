package com.highfive.refurmoa.admin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
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
	 public PartnerDTO findPartner(int search) {
		 int cnt=repository.countCom(search);
		 PartnerDTO tmp= new PartnerDTO(partnerRepository.findByComNum(search),cnt);
		 return tmp;
	 }
	 @Override
	 public PartnerDTO changState(int com_num) {
		 
		 partnerRepository.changState(com_num);
		 int cnt=repository.countCom(com_num);
		 PartnerDTO tmp= new PartnerDTO(partnerRepository.findByComNum(com_num),cnt);
		 return tmp;
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
	
	@Override
	public int updatePartner(ProdPartner partner) {
		partnerRepository.save(partner);
		return 1;
	}
	
	@Override
	public  Page<ProdListDTO>partnerProduct(int num, String search,Pageable pageable){
		List<Product> tmp=repository.findPartnerProd(num,search);
		List<ProdListDTO> list=new ArrayList<ProdListDTO>();
		Date now=new Date();
		for(int i=0;i<tmp.size();i++) {
			int sell_status=0;
			 Date[] date=repository.getDate(tmp.get(i).getProductCode());
			 if(tmp.get(i).getProdState()==1) {
				if(now.compareTo(date[0])==1)sell_status=1;
				else sell_status=2;
			 }
			 else if(tmp.get(i).getProdState()==2)sell_status=3;
			 else if(tmp.get(i).getProdState()==5)sell_status=4;
			ProdListDTO temp=new ProdListDTO(tmp.get(i),sell_status);
			list.add(temp);
		}	
		PageRequest pageRequest = PageRequest.of(pageable.getPageNumber(),pageable.getPageSize());
		int start = (int) pageRequest.getOffset();
		int end = Math.min((start + pageRequest.getPageSize()), list.size());
		Page<ProdListDTO> Page = new PageImpl<>(list.subList(start, end), pageRequest, list.size());
		return Page;	
	}
}
