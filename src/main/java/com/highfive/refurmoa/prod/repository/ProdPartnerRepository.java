package com.highfive.refurmoa.prod.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.ProdPartner;

public interface ProdPartnerRepository extends JpaRepository<ProdPartner, Integer>{
	


}
