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
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
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
   public Page<PartnerDTO> Adminpartner(String search, Pageable pageable) {
	   Page<ProdPartner> tmp=partnerRepository.findComAdmin(search,pageable);
	   Page<PartnerDTO> Page =tmp.map(partner->{
		   int cnt=repository.countCom(partner.getComNum());
		   return new PartnerDTO(partner,cnt);	
	   });
	   return Page;		
   }
   @Override
   public int insertPartner(ProdPartner partner) {
	   partnerRepository.save(partner);
	   return 1;
   }
   @Override
   public int updatePartner(ProdPartner partner) {
	   partnerRepository.save(partner);
	   return 1;
   }
   
   @Override
   public  Page<ProdListDTO>partnerProduct(int num, String search,Pageable pageable){
	   Date now=new Date();
	   ProdPartner pp= partnerRepository.findByComNum(num);
	   Page<Product> tmp=repository.findPartnerProd(pp,search,pageable);
	   Page<ProdListDTO> Page =tmp.map(partner->{
		   int sell_status=0;
		   Date[] date=repository.getDate(partner.getProductCode());
		   if(partner.getProdState()==1) {
			   if(now.compareTo(date[0])==1)sell_status=1;
			   else sell_status=2;
			}
			else if(partner.getProdState()==2)sell_status=3;
			else if(partner.getProdState()==5)sell_status=4;
		   return new ProdListDTO(partner,sell_status);	
	   });
	   return Page;		
	   
   }
}
