package com.highfive.refurmoa.admin.dto.response;

import com.highfive.refurmoa.entity.Banner;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetBannerResponseDTO {
    private int bann_num;
    private String bann_image;
    private String seller_name;
    private String seller_phone;
    private String bann_link;
    private String bann_ref;
    private Date bann_start;
    private Date bann_end;

    public GetBannerResponseDTO(final Banner banner) {
        this.bann_num = banner.getBannNum();
        this.bann_image = banner.getBannImage();
        this.seller_name = banner.getSellerName();
        this.seller_phone = banner.getSellerPhone();
        this.bann_link = banner.getBannLink();
        this.bann_ref = banner.getBannRef();
        this.bann_start = banner.getBannStart();
        this.bann_end = banner.getBannEnd();
    }

}
