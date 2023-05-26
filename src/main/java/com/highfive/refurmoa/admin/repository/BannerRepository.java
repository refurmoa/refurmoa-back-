package com.highfive.refurmoa.admin.repository;

import com.highfive.refurmoa.entity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BannerRepository extends JpaRepository<Banner, Integer> {
    Banner findByBannNum(int bannNum);
}
