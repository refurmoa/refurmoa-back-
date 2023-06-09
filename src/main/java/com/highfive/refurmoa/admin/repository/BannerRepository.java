package com.highfive.refurmoa.admin.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Banner;

import jakarta.transaction.Transactional;


public interface BannerRepository extends JpaRepository<Banner, Integer> {

    Page<Banner> findAllByOrderByBannNumDesc(Pageable pageable); // 배너 목록 조회
    @Transactional
    void deleteByBannNum(int bann_num); // 배너 삭제
    Page<Banner> findBySellerNameContaining(String search, Pageable pageable); // 배너 목록 검색

    @Query("select p from Banner p where p.bannLocation ='top' and p.bannEnd>:date and p.bannStart< :date")
    List<Banner> findRef(@Param("date")Date date);
    
    @Query("select p from Banner p where p.bannLocation ='bottom'")
    List<Banner> findAd();
}
