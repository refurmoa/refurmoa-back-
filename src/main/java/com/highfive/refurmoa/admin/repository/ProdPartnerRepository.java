package com.highfive.refurmoa.admin.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.ProdPartner;

public interface ProdPartnerRepository extends JpaRepository<ProdPartner, Integer>{
	 
     // 제휴회사 검색
     @Query(value="select * from prod_partner where com_name like CONCAT('%',:search,'%') and com_status=1 ",nativeQuery=true)
	 List<ProdPartner> findByComNameContaining(@Param("search")String search);

     @Query(value="select * from prod_partner where com_name like CONCAT('%',:search,'%')",nativeQuery=true)
	List<ProdPartner> findComAdmin(@Param("search")String search);
}
