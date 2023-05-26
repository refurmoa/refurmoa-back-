package com.highfive.refurmoa.prod.service;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.repository.ProdPartnerRepository;
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
	public void insertProd(int comNum,Product prod) {
			ProdPartner partner=partnerRepository.findById(comNum).orElse(null);
			prod.setComNum(partner);		
	}
	@Override
	public int ProductWrite(ProductWriteDTO prodDto) {
		
		ProdPartner tmp =new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(),prodDto.getCategory(),prodDto.getCategory_code(),prodDto.getDeffect_image1(),prodDto.getDeffect_image2(),
				prodDto.getDeffect_image3(),prodDto.getDeffect_text(),prodDto.isGuarantee(),prodDto.getMain_image(),prodDto.getOrg_price(),prodDto.getProd_com(),
				prodDto.getProd_grade(),prodDto.getProd_name(),prodDto.getProd_state(),prodDto.getReg_date(),tmp);	
		insertProd(prodDto.getCom_num(),productEntity);
        repository.save(productEntity);
		return productEntity.getProductCode();
	}
	@Override
	public void insertFile(ProdFileDTO dto) {
		repository.insert(dto.getProdNum(),dto.getUp_main_image(),dto.getUp_deffect_image1(),dto.getUp_deffect_image2(),dto.getUp_deffect_image3());
	}
	@Override
	public Product productInfo(int productCode){
		return repository.productInfo(productCode);		
	}
	
	
}
