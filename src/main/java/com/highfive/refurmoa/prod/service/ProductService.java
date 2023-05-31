package com.highfive.refurmoa.prod.service;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;



public interface ProductService {
	
	public List<ProdListDTO> productList(String category,String status);//상품 목록
	public int productDelete(int code);//상품 삭제
//	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException;//상품 등록
//	public int ProductUpdate(MultipartFile mainImg,ProductWriteDTO prodDto)throws IOException;// 상품 수정
	public void insertFile(ProdFileDTO dto);//상품 이미지 저장
	public ProdResponseDTO productInfo(int productCode);// 상품 정보 조회
	public void insertProd(int comNum,Product prod);//상품 제휴회사 검색
}
