package com.highfive.refurmoa.prod.service;



import com.highfive.refurmoa.prod.DTO.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.ProductWriteDTO;

public interface ProductService {
	public int ProductWrite(ProductWriteDTO prodDto);
	public void insertFile(ProdFileDTO dto);
}
