package com.highfive.refurmoa.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ProductContorller {
	
	private int prodNum;
	
	private final ProductServiceImpl ProductServiceImpl;
//	@PostMapping("/product/write")
//    public int ProductWrite(@RequestBody  ProductWriteDTO prodDto)   {
//		prodNum=ProductServiceImpl.ProductWrite(prodDto);
//        return prodNum;
//    }
	@PostMapping("/product/write")
    public int ProductUpdate(@RequestParam(value="main_image") MultipartFile main_image,
    		@RequestParam(value="uploadfiles") MultipartFile[] uploadfiles, ProductWriteDTO prodDto) throws IOException   {
//		prodNum=ProductServiceImpl.ProductWrite(main_image,uploadfiles,prodDto);
		System.out.println(main_image);
		System.out.println(uploadfiles);
		System.out.println(prodDto.getDeffect_text());
        return prodNum;
    }

	
	@GetMapping("/product/updateInfo")
	 public ProdResponseDTO productInfo(@RequestParam(value="product_code") int productCode) {
        return ProductServiceImpl.productInfo(productCode);
    }

}
