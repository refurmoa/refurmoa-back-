package com.highfive.refurmoa.prod.service;

import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	 private ProductRepository repository;

	 public ProductServiceImpl(ProductRepository repository) {
	        this.repository = repository;
	 }
	@Override
	public int ProductWrite(ProductWriteDTO prodDto) {
		
		Product productEntity = new Product(prodDto.getProduct_code(),prodDto.getCategory(),prodDto.getCategory_code(),prodDto.getDeffect_image1(),prodDto.getDeffect_image2(),
				prodDto.getDeffect_image3(),prodDto.getDeffect_text(),prodDto.isGuarantee(),prodDto.getMain_image(),prodDto.getOrg_price(),prodDto.getProd_com(),
				prodDto.getProd_grade(),prodDto.getProd_name(),prodDto.getProd_state(),prodDto.getReg_date(),prodDto.getCom_num());
        repository.save(productEntity);
		return productEntity.getProductCode();
	}
	@Override
	public void insertFile(ProdFileDTO dto) {
		repository.insert(dto.getProdNum(),dto.getUp_main_image(),dto.getUp_deffect_image1(),dto.getUp_deffect_image2(),dto.getUp_deffect_image3());
	}
	
}
