package com.highfive.refurmoa.prod.service;

import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;


public interface ProductService {
	public int ProductWrite(ProductWriteDTO prodDto);
	public void insertFile(ProdFileDTO dto);
	public Product productInfo(int productCode);
	public void insertProd(int comNum,Product prod);
}
