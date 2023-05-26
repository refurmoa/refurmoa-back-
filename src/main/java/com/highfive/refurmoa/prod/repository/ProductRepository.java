package com.highfive.refurmoa.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository  extends JpaRepository<Product, String>{
	
	@Query(value="select * from prod_partner where com_num=:num",nativeQuery=true)
	ProdPartner getPartner(@Param("num")int num );
	
	@Transactional
	@Modifying
	@Query(value="update product set main_image=:main,defect_image1=:defect1,defect_image2=:defect2,defect_image3=:defect3  where product_code=:num",nativeQuery=true)
	int insert(@Param("num")int num,@Param("main")String main,@Param("defect1")String defect1,@Param("defect2")String defect2,@Param("defect3")String defect3 );
	
	
	
	@Query(value="select * from product where prodcut_code=:num",nativeQuery=true)
	Product productInfo(@Param("num")int num );
}


