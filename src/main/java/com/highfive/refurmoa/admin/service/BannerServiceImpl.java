package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import com.highfive.refurmoa.admin.repository.BannerRepository;
import com.highfive.refurmoa.entity.Banner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Service
public class BannerServiceImpl implements BannerService {

    @Value("${spring.servlet.multipart.location}")
    String imageDir;

    private BannerRepository repository;

    public BannerServiceImpl(BannerRepository repository) {
        this.repository = repository;
    }

    // 배너 목록 조회
    @Override
    public Page<Banner> getListBanner(Pageable pageable) {
        return repository.findAll(pageable);
    }

    // 배너 목록 검색
    @Override
    public Page<Banner> searchBanner(String search, Pageable pageable) {
        return repository.findBySellerNameContaining(search, pageable);
    }

    // 배너 등록
    @Override
    public int writeBanner(WriteBannerRequestDTO writeBannerRequestDTO, MultipartFile banner_img) throws IOException {
        String banner_image = saveImage(banner_img); // 이미지파일 이름
        Banner banner = new Banner(banner_image, writeBannerRequestDTO);
        try {
            repository.save(banner);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    // 이미지 파일 이름 만들기
    private String saveImage(MultipartFile imageFile) throws IOException {
        String imgName = UUID.randomUUID() + "." +StringUtils.getFilename(imageFile.getOriginalFilename());
        File file = new File(imageDir + "banner\\" + imgName);
        imageFile.transferTo(file);
        return imgName;
    }

    // 배너 삭제
    @Override
    public int deleteBanner(int bann_num) {
        repository.deleteByBannNum(bann_num);
        return 1;
    }
}
