package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.entity.Banner;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface BannerService {
    public Banner getBanner(int bannnum);
    public List<Banner> getListBanner();
    public int writeBanner(WriteBannerRequestDTO writeBannerRequestDTO, MultipartFile banner_img) throws IOException;
}
