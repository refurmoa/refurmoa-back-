package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.admin.repository.BannerRepository;
import com.highfive.refurmoa.entity.Banner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
public class BannerServiceImpl implements BannerService {

    @Value("${spring.servlet.multipart.location}")
    String imageDir;

    private BannerRepository repository;

    public BannerServiceImpl(BannerRepository repository) {
        this.repository = repository;
    }

    @Override
    public Banner getBanner(int bannnum) {
        return repository.findByBannNum(bannnum);
    }

    @Override
    public List<Banner> getListBanner() {
        return repository.findAll();
    }

    @Override
    public int writeBanner(WriteBannerRequestDTO writeBannerRequestDTO, MultipartFile banner_img) throws IOException {
        String banner_image = saveImage(banner_img); // 이미지파일 이름
        Banner banner = new Banner(banner_image, writeBannerRequestDTO);
        repository.save(banner);
        return 1;
    }
    private String saveImage(MultipartFile imageFile) throws IOException {
        String imgName = UUID.randomUUID() + "." +StringUtils.getFilename(imageFile.getOriginalFilename());
        File file = new File(imageDir + "banner\\" + imgName);
        imageFile.transferTo(file);
        return imgName;
    }
}
