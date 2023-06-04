package com.highfive.refurmoa.prod.controller;

import com.highfive.refurmoa.prod.service.ProductServiceImpl;

import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListResponseDTO;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prod")
public class ProductController {

	private final ProductServiceImpl ProductServiceImpl;

	// 상품 목록 조회
	@GetMapping("")
	public Page<ProdListResponseDTO> productList(@RequestParam String search, @RequestParam String category, @RequestParam String status, Pageable pageable) {
		return ProductServiceImpl.productList(search, category, status, pageable);
	}

	// 상품 삭제
	@GetMapping("/delete")
	public int productList(@RequestParam(value="product_code") int code) {
		return ProductServiceImpl.productDelete(code);
	}

	private int prodNum;

	// 상품 등록
	@PostMapping("/write")
    public int ProductWrite(@RequestParam(value="main_image") MultipartFile mainImg, ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		prodNum = ProductServiceImpl.ProductWrite(mainImg, prodDto);
		return prodNum;
	}

	// 상품 수정
	@PostMapping("/update")
	public int ProductUpdate(@RequestParam(value="main_image") MultipartFile mainImg, ProductWriteDTO prodDto) throws IllegalStateException, IOException {
  	return ProductServiceImpl.ProductUpdate(mainImg, prodDto);
	}

	// 상품 이미지 저장
	@PostMapping("/file")
	public int upload(@RequestBody MultipartFile[] uploadfiles, int prodNum) throws IOException {
		int prod_num = prodNum;
		String[] tmp = new String[]{"","",""};
		for (int i = 0; i < uploadfiles.length; i++) {
			File defect = new File("prod\\"+ UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			uploadfiles[i].transferTo(defect);
			tmp[i] = defect.toString();
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		ProductServiceImpl.insertFile(dto);
		return 1;
	}

	// 상품 정보 조회
	@GetMapping("/update/info")
	public ProdResponseDTO productInfo(@RequestParam(value="product_code") int productCode) {
        return ProductServiceImpl.productInfo(productCode);
	}

}
