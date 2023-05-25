package com.highfive.refurmoa.admin.controller;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.admin.service.BannerServiceImpl;
import com.highfive.refurmoa.entity.Banner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BannerController {

    private final BannerServiceImpl bannerServiceImpl;

    @GetMapping("/admin/banner")
    public List<Banner> getListBanner() {
        return bannerServiceImpl.getListBanner();
    }

    @PostMapping("/admin/banner/write")
    public int writeBanner(@RequestParam(value="banner_img") MultipartFile banner_img,
                           WriteBannerRequestDTO writeBannerRequestDTO) throws Exception {
        bannerServiceImpl.writeBanner(writeBannerRequestDTO,banner_img);
        return 1;
    }

}
