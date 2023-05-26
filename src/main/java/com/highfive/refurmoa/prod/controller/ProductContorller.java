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

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ProductContorller {
	
	private int prodNum;
	
	private final ProductServiceImpl ProductServiceImpl;
	@PostMapping("/product/write")
    public int ProductWrite(@RequestBody  ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		prodNum=ProductServiceImpl.ProductWrite(prodDto);
        return prodNum;
    }

	@PostMapping("/uploadfile")
	public int upload(@RequestBody MultipartFile[] uploadfiles) throws IOException {
       
		int prod_num = prodNum;
		System.out.println("prod_num : " + prod_num);
		File main = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		uploadfiles[0].transferTo(main);
		File defect1 = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		uploadfiles[1].transferTo(defect1);
		File defect2 = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		uploadfiles[2].transferTo(defect2);
		File defect3 = new File(UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		uploadfiles[3].transferTo(defect3);
		ProdFileDTO dto= new ProdFileDTO(prod_num,uploadfiles[0].getOriginalFilename(),main.toString(),uploadfiles[1].getOriginalFilename(),defect1.toString()
				,uploadfiles[2].getOriginalFilename(),defect2.toString(),uploadfiles[3].getOriginalFilename(),defect3.toString() );
		ProductServiceImpl.insertFile(dto);
		return 1;
    }
	@GetMapping("/product/update")
	 public Product ProductWrite(@RequestParam int productCode) {
        return ProductServiceImpl.productInfo(productCode);
    }

}
