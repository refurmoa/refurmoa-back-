package com.highfive.refurmoa.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.repository.ProdPartnerRepository;
import com.highfive.refurmoa.prod.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Value("${spring.servlet.multipart.location}")
    String imageDir;
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
	public int ProductWrite(MultipartFile mainImg,MultipartFile[] uploadfiles,ProductWriteDTO prodDto ) throws IOException{
		
		ProdPartner tmp =new ProdPartner();
		String main = saveImage(mainImg); 
		String[] deffect={"", "", ""};
		
		for(int i=0;i<uploadfiles.length;i++) deffect[i]=saveImage(uploadfiles[i]);
		
		// Product prodEntity = new Product(main,deffect, prodDto);
		// insertProd(prodDto.getCom_num(),prodEntity);
        // repository.save(prodEntity);
		return 1;
	}
	
	private String saveImage(MultipartFile imageFile) throws IOException {
        String imgName = UUID.randomUUID() + "." +StringUtils.getFilename(imageFile.getOriginalFilename());
        File file = new File(imageDir + "prod\\" + imgName);
        imageFile.transferTo(file);
        return imgName;
    }
	
	@Override
	public ProdResponseDTO productInfo(int productCode){
		Product tmp=repository.findById(productCode).orElse(null);
		int tmpNum=repository.comNumInfo(productCode);
		ProdResponseDTO dto =new ProdResponseDTO();
		dto.setProd(tmp);
		dto.setCom_name(repository.comName(tmpNum));
		dto.setCom_num(tmpNum);
		return dto;
	}
//	@Override
//	public int ProductUpdate(ProductWriteDTO prodDto) {
//		ProdPartner tmp =new ProdPartner();
//		Product productEntity = new Product(prodDto.getProduct_code(),prodDto.getCategory(),prodDto.getCategory_code(),prodDto.getDeffect_image1(),prodDto.getDeffect_image2(),
//				prodDto.getDeffect_image3(),prodDto.getDeffect_text(),prodDto.isGuarantee(),prodDto.getMain_image(),prodDto.getOrg_price(),prodDto.getProd_com(),
//				prodDto.getProd_grade(),prodDto.getProd_name(),prodDto.getProd_state(),prodDto.getReg_date(),tmp);	
//		insertProd(prodDto.getCom_num(),productEntity);
//		repository.save(productEntity);
//		return productEntity.getProductCode();
//	}
}
