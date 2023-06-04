package com.highfive.refurmoa.prod.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;

import jakarta.transaction.Transactional;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p " +
			"WHERE p.prodName LIKE %:search% AND p.category LIKE %:category% AND p.prodState IN :status " +
			"ORDER BY CASE WHEN p.prodState = 0 THEN true ELSE false END DESC")
	Page<Product> getListProduct(@Param("search") String search, @Param("category") String category, @Param("status") List<Integer> status, Pageable pageable);

	@Transactional
	@Modifying
	@Query(value="update product set defect_image1=:defect1,defect_image2=:defect2,defect_image3=:defect3  where product_code=:num", nativeQuery=true)
	int insert(@Param("num") int num,@Param("defect1") String defect1, @Param("defect2") String defect2, @Param("defect3") String defect3 );

	@Query(value="select main_image from product where product_code=:num", nativeQuery=true)
	String MainInfo(@Param("num") int num );

	@Query(value="select count(*) from product where com_num=:num", nativeQuery=true)
	int countCom(@Param("num") int num);

	@Query(value="select start_date,end_date from board where product_code=:num", nativeQuery=true)
	Date[] getDate(@Param("num") int num);

	@Transactional
	void deleteById(int code);
	
	@Query(value="select * from product where prod_name like CONCAT('%',:name,'%') and category_code like CONCAT('%',:search,'%')",nativeQuery=true)
	 List<Product> findProdList(@Param("name")String name,@Param("search")String search);
  
	 @Query("select p from Product p where p.comNum =:num and p.prodName like %:name% order by p.prodState")
     Page<Product> findPartnerProd(@Param("num")ProdPartner num,@Param("name")String name,Pageable pageable);
	 
	 @Query("select p from Product p where p.prodName like %:search% and p.prodState=0")
	 Page<Product> findProduct(@Param("search")String search,Pageable pageable);

	 // 구매 확정
	Product findByProductCode(int productCode);
}


