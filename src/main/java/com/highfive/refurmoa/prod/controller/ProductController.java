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

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/prod")
public class ProductController {

	private final ProductServiceImpl productServiceImpl;
	 @Value("${spring.servlet.multipart.location}")
	  String imageDir;
	 
	 private String saveImage(MultipartFile imageFile) throws IOException {
	        String imgName = UUID.randomUUID() + "." +StringUtils.getFilename(imageFile.getOriginalFilename());
	        File file = new File(imageDir + "prod\\" + imgName);
	        imageFile.transferTo(file);
	        return imgName;
	   }
	// 상품 목록 조회
	@GetMapping("")
	public Page<ProdListResponseDTO> productList(@RequestParam String search, @RequestParam String category, @RequestParam String status, Pageable pageable) {
		return productServiceImpl.productList(search, category, status, pageable);
	}

	// 상품 삭제
	@GetMapping("/delete")
	public int productList(@RequestParam(value="product_code") int code) {
		return productServiceImpl.productDelete(code);
	}

	private int prodNum;

	// 상품 등록
	@PostMapping("/write")
  public int ProductWrite(@RequestParam(value="main_image") MultipartFile mainImg, ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		prodNum = productServiceImpl.ProductWrite(mainImg, prodDto);
		return prodNum;
	}
  // 상품 수정
	@PostMapping("/update")
  public int ProductUpdate(@RequestParam(value="main_image", required = false) MultipartFile mainImg, ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
  	return productServiceImpl.ProductUpdate(mainImg, prodDto);
	}

	// 상품 이미지 저장
	@PostMapping("/file")
	public int upload(@RequestBody MultipartFile[] uploadfiles, int prodNum) throws IOException {
		int prod_num = prodNum;
		String[] tmp = new String[]{null,null,null};
		for (int i = 0; i < uploadfiles.length; i++) {
			tmp[i] = saveImage(uploadfiles[i]);
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		productServiceImpl.insertFile(dto);
		return 1;
	}

	// 상품 정보 조회
	@GetMapping("/update/info")
	public ProdResponseDTO productInfo(@RequestParam(value="product_code") int productCode) {
        return productServiceImpl.productInfo(productCode);
	}

	// 상품 현황 변경
	@GetMapping("/change")
	public void updateProdState(@RequestParam int productCode) {
		productServiceImpl.updateProdState(productCode);
	}
	
}
