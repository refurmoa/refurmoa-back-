package com.highfive.refurmoa.prod.service;

import java.io.IOException;
import java.util.List;

import com.highfive.refurmoa.prod.DTO.response.ProdListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;



public interface ProductService {

	Page<ProdListResponseDTO> productList(String search, String category, String status, Pageable pageable); // 상품 목록 조회
	
	public List<ProdListDTO> productList(String category,String status);
	public List<ProdListDTO> productSearch(ProdSearchDTO body);
	public int productDelete(int code);
	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException;
	public int ProductUpdate(MultipartFile mainImg,ProductWriteDTO prodDto)throws IOException;
	public void insertFile(ProdFileDTO dto);
	public ProdResponseDTO productInfo(int productCode);
	public void insertProd(int comNum,Product prod);
	
}
