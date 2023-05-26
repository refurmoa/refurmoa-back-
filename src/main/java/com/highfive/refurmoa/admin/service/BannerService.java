package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.entity.Banner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BannerService {
    public Page<Banner> getListBanner(Pageable pageable); // 배너 목록 조회
    public Page<Banner> searchBanner(String search, Pageable pageable); // 배너 목록 검색
    public int writeBanner(WriteBannerRequestDTO writeBannerRequestDTO, MultipartFile banner_img) throws IOException; // 배너 등록
    public int deleteBanner(int bannerNum); // 배너 삭제
}
