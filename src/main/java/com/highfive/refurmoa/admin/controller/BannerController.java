package com.highfive.refurmoa.admin.controller;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.admin.dto.response.GetBannerResponseDTO;
import com.highfive.refurmoa.admin.service.BannerServiceImpl;
import com.highfive.refurmoa.entity.Banner;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequiredArgsConstructor
public class BannerController {

    private final BannerServiceImpl bannerServiceImpl;

    // 배너 목록 조회
    @GetMapping("/admin/banner")
    public Page<GetBannerResponseDTO> getListBanner(Pageable pageable) {
        Page<Banner> banner = bannerServiceImpl.getListBanner(pageable);
        Page<GetBannerResponseDTO> getBannerResponseDTOS = banner.map(GetBannerResponseDTO::new);
        return getBannerResponseDTOS;
    }

    // 배너 목록 검색
    @GetMapping("/admin/banner/search")
    public Page<GetBannerResponseDTO> searchBanner(@RequestParam String search, Pageable pageable) {
        Page<Banner> banner = bannerServiceImpl.searchBanner(search, pageable);
        Page<GetBannerResponseDTO> getBannerResponseDTOS = banner.map(GetBannerResponseDTO::new);
        return getBannerResponseDTOS;
    }

    // 배너 등록
    @PostMapping("/admin/banner/write")
    public int writeBanner(@RequestParam(value="banner_img") MultipartFile banner_img,
                           WriteBannerRequestDTO writeBannerRequestDTO) throws Exception {
        return bannerServiceImpl.writeBanner(writeBannerRequestDTO,banner_img);
    }

    // 배너 삭제
    @GetMapping("/admin/banner/delete")
    public int deleteBanner(@RequestParam("banner_num") int bann_num) {
        return bannerServiceImpl.deleteBanner(bann_num);
    }

}
