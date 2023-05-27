package com.highfive.refurmoa.cs.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FaqBoardUpdateDTO {
    private int faq_num;
    private int faq_cate;
    private String faq_title;
    private String faq_content;
}
