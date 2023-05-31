package com.highfive.refurmoa.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	 private ProductRepository repository;
	 private ProdPartnerRepository partnerRepository;

	 public ProductServiceImpl(ProductRepository repository,ProdPartnerRepository partnerRepository ) {
	        this.repository = repository;
	        this.partnerRepository=partnerRepository;
	 }
	 
	 @Override
	 public List<ProdListDTO> productList(String category,String status) {
		  
		 String cate="";
		 switch(category) {
			 case "all": cate="";
			 break;
			 case "funiture": cate= "fun" ;
			 break;
			 case  "appliance": cate= "app";
			 break;
			 default: cate=category;	 
		 }

		 Date now=new Date();
		 List<Product> prodlist=repository.findProduct(cate);
		 List<ProdListDTO> temp =new ArrayList<ProdListDTO>();
		 
		 for(int i=0;i<prodlist.size();i++) {
			 int sell_status=0;
			 Date[] date=repository.getDate(prodlist.get(i).getProductCode());
			 if(prodlist.get(i).getProdState()==1) {
				if(now.compareTo(date[0])==1)sell_status=1;
				else sell_status=2;
			 }
			 else if(prodlist.get(i).getProdState()==2)sell_status=3;
			 else if(prodlist.get(i).getProdState()==5)sell_status=4;
			 System.out.println(sell_status);
			 switch(status) {
			 case "all":
				 ProdListDTO tmp=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp);
				 break;
			 case "ingnend": if(sell_status>0) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 case  "yetnend": if(sell_status==4||sell_status==0) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 case "yetning": if(sell_status<4) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 case "yet":if(sell_status==0) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 case  "ing": if(sell_status>0&&sell_status<4) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 case  "end": if(sell_status==4) {
				 ProdListDTO tmp1=new ProdListDTO(prodlist.get(i),sell_status);
				 temp.add(tmp1);
			 }
			 break;
			 default: break;	 
		 }
			 
			 
		 }
		 
		 return temp;
	 }
	 
	@Override
	public int productDelete(int code) {
		repository.deleteById(code);
		return 1;
	}
	@Override
	public void insertProd(int comNum,Product prod) {
			ProdPartner partner=partnerRepository.findById(comNum).orElse(null);
			prod.setComNum(partner);		
	}
//	@Override
//	public int ProductUpdate(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException {
//		String mainName = null;
//		if(mainImg.getSize()!=0) {
//			File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
//			mainImg.transferTo(main);
//			mainName=main.toString();
//		}else {
//			mainName=repository.MainInfo(prodDto.getProduct_code());
//		}
//		ProdPartner tmp =new ProdPartner();
//		Product productEntity = new Product(prodDto.getProduct_code(),prodDto.getCategory(),prodDto.getCategory_code(),prodDto.getDeffect_image1(),prodDto.getDeffect_image2(),
//				prodDto.getDeffect_image3(),prodDto.getDeffect_text(),prodDto.isGuarantee(),mainName,prodDto.getOrg_price(),prodDto.getProd_com(),
//				prodDto.getProd_grade(),prodDto.getProd_name(),prodDto.getProd_state(),prodDto.getReg_date(),tmp);	
//		insertProd(prodDto.getCom_num(),productEntity);
//        repository.save(productEntity);
//		return productEntity.getProductCode();
//	}
//	@Override
//	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException {
//		
//		File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
//		mainImg.transferTo(main);
//
//		ProdPartner tmp =new ProdPartner();
//		Product productEntity = new Product(prodDto.getProduct_code(),prodDto.getCategory(),prodDto.getCategory_code(),prodDto.getDeffect_image1(),prodDto.getDeffect_image2(),
//				prodDto.getDeffect_image3(),prodDto.getDeffect_text(),prodDto.isGuarantee(),main.toString(),prodDto.getOrg_price(),prodDto.getProd_com(),
//				prodDto.getProd_grade(),prodDto.getProd_name(),prodDto.getProd_state(),prodDto.getReg_date(),tmp);	
//		insertProd(prodDto.getCom_num(),productEntity);
//        repository.save(productEntity);
//		return productEntity.getProductCode();
//	}
	
	@Override
	public void insertFile(ProdFileDTO dto) {
		repository.insert(dto.getProdNum(),dto.getUp_deffect_image1(),dto.getUp_deffect_image2(),dto.getUp_deffect_image3());
	}
	@Override
	public ProdResponseDTO productInfo(int productCode){
		Product tmp=repository.findById(productCode).orElse(null);
		ProdResponseDTO dto =new ProdResponseDTO(tmp);
		return dto;
	}
}
