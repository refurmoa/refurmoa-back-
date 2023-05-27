package com.highfive.refurmoa.cs.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class FaqBoardWriteDTO {
    private int faq_cate;
    private String faq_title;
    private String faq_content;
    private Date faq_date;
}
