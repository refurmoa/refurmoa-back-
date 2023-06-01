package com.highfive.refurmoa.prod.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor

public class ProductController {

	
	private final ProductServiceImpl ProductServiceImpl;


	@GetMapping("/prod")
	public Page<ProdListResponseDTO> productList(@RequestParam String search, @RequestParam String category, @RequestParam String status, Pageable pageable) {
		return ProductServiceImpl.productList(search, category, status, pageable);
	}


	
	
//	 @GetMapping("/prod")
//	 public List<ProdListDTO> productList(@RequestParam(value="category") String category,@RequestParam(value="sell_status") String status) {
//
//       return ProductServiceImpl.productList(category,status);
//	 }
	 @PostMapping("/prod/search")
	 public List<ProdListDTO> productSearch(@RequestBody ProdSearchDTO body) {
       return ProductServiceImpl.productSearch(body);
	 }
	 @GetMapping("/prod/delete")
	 public int productList(@RequestParam(value="product_code") int code) {
		
       return ProductServiceImpl.productDelete(code);
   }
   private int prodNum;
	@PostMapping("/prod/write")
    public int ProductWrite(@RequestParam(value="main_image") MultipartFile mainImg,ProductWriteDTO prodDto) throws IllegalStateException, IOException  {
		prodNum=ProductServiceImpl.ProductWrite(mainImg,prodDto);

        return prodNum;
    }
	@PostMapping("/prod/update")
    public int ProductUpdate(@RequestParam(value="main_image") MultipartFile mainImg,ProductWriteDTO prodDto) throws IllegalStateException, IOException  {

        return ProductServiceImpl.ProductUpdate(mainImg,prodDto);
    }
	@PostMapping("/prod/file")
	public int upload(@RequestBody MultipartFile[] uploadfiles) throws IOException {
       
		int prod_num = prodNum;
		String[] tmp=new String[]{"","",""};
		for (int i=0;i<uploadfiles.length;i++) {
			File defect = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			uploadfiles[i].transferTo(defect);
			tmp[i]=defect.toString();
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		ProductServiceImpl.insertFile(dto);
		return 1;
    }
	
	@GetMapping("/prod/update/info")
	 public ProdResponseDTO productInfo(@RequestParam(value="product_code") int productCode) {
        return ProductServiceImpl.productInfo(productCode);
    }
	@GetMapping("/post/prod_search")
	 public Page<FindProductDTO> findProduct(@RequestParam(value="search") String search, Pageable pageable) {
		return ProductServiceImpl.findProduct(search,pageable);
	}
		
	

}
