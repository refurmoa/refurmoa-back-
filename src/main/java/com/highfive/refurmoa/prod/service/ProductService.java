package com.highfive.refurmoa.prod.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;



public interface ProductService {
	
	public List<ProdListDTO> productList(String category,String status);
	public List<ProdListDTO> productSearch(ProdSearchDTO body);
	public int productDelete(int code);
	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException;
	public int ProductUpdate(MultipartFile mainImg,ProductWriteDTO prodDto)throws IOException;
	public void insertFile(ProdFileDTO dto);
	public ProdResponseDTO productInfo(int productCode);
	public void insertProd(int comNum,Product prod);
	
}
