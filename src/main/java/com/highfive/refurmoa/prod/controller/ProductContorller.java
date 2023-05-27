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
	@PostMapping("/product/write")
    public int ProductWrite(@RequestParam(value="main_image") MultipartFile mainImg,ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		prodNum=ProductServiceImpl.ProductWrite(mainImg,prodDto);

        return prodNum;
    }
	@PostMapping("/product/update")
    public int ProductUpdate(@RequestParam(value="main_image") MultipartFile mainImg,ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		
        return ProductServiceImpl.ProductUpdate(mainImg,prodDto);
    }
	@PostMapping("/uploadfile")
	public int upload(@RequestBody MultipartFile[] uploadfiles) throws IOException {
       
		int prod_num = prodNum;
		String[] tmp=new String[]{"","",""};
		for (int i=0;i<uploadfiles.length;i++) {
			File defect = new File("\\prod"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			uploadfiles[i].transferTo(defect);
			tmp[i]=defect.toString();
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		ProductServiceImpl.insertFile(dto);
		return 1;
    }
	
	@GetMapping("/product/updateInfo")
	 public ProdResponseDTO productInfo(@RequestParam(value="product_code") int productCode) {
        return ProductServiceImpl.productInfo(productCode);
    }

}
