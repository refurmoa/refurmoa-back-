package com.highfive.refurmoa.admin.repository;

import com.highfive.refurmoa.entity.Banner;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BannerRepository extends JpaRepository<Banner, Integer> {

    Page<Banner> findAllByOrderByBannNumDesc(Pageable pageable); // 배너 목록 조회
    @Transactional
    void deleteByBannNum(int bann_num); // 배너 삭제
    Page<Banner> findBySellerNameContaining(String search, Pageable pageable); // 배너 목록 검색
}
