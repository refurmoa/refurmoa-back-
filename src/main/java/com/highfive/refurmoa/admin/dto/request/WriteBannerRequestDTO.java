package com.highfive.refurmoa.admin.dto.request;

import lombok.*;

import java.util.Date;

@Getter
@Setter
public class WriteBannerRequestDTO {
    private String seller_name;
    private String seller_phone;
    private String bann_link;
    private String bann_ref;
    private Date bann_start;
    private Date bann_end;
    private String bann_location;
}
