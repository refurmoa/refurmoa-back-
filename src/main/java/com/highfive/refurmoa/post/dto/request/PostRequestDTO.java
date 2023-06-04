package com.highfive.refurmoa.post.dto.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostRequestDTO {
    private String search;
    private String sell_type;
    private String category;
    private String sell_status;
    private String orderby;
    private String member_id;
    private int page;
    private int size;
}
