package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.admin.dto.request.WriteBannerRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "bann_num", nullable = false)
    private int bannNum;

    @Column(name = "bann_image", nullable = false, length = 100)
    private String bannImage;

    @Column(name = "seller_name", nullable = false, length = 15)
    private String sellerName;

    @Column(name = "seller_phone", nullable = false, length = 15)
    private String sellerPhone;

    @Column(name = "bann_link", length = 200)
    private String bannLink;

    @Column(name = "bann_ref", length = 50)
    private String bannRef;

    @Column(name = "bann_start", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bannStart;

    @Column(name = "bann_end", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date bannEnd;

    @Column(name = "bann_location", nullable = false)
    private String bannLocation;

    public Banner(String bannerImage, WriteBannerRequestDTO writeBannerRequestDTO) {
        this.bannImage = bannerImage;
        this.sellerName = writeBannerRequestDTO.getSeller_name();
        this.sellerPhone = writeBannerRequestDTO.getSeller_phone();
        this.bannLink = writeBannerRequestDTO.getBann_link();
        this.bannRef = writeBannerRequestDTO.getBann_ref();
        this.bannStart = writeBannerRequestDTO.getBann_start();
        this.bannEnd = writeBannerRequestDTO.getBann_end();
        this.bannLocation = writeBannerRequestDTO.getBann_location();
    }
}
