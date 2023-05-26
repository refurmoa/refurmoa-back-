package com.highfive.refurmoa.prod.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;


public interface ProductService {
	
	public int ProductWrite(MultipartFile mainImg,MultipartFile[] uploadfiles,ProductWriteDTO prodDto ) throws IOException;
//	public int ProductUpdate(ProductWriteDTO prodDto);

	public ProdResponseDTO productInfo(int productCode);
	public void insertProd(int comNum,Product prod);
}
