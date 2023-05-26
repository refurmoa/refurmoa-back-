package com.highfive.refurmoa.cs.dto.response;

import com.highfive.refurmoa.entity.FaqBoard;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class GetFaqBoardResponseDTO {
    private int faq_num;
    private int faq_cate;
    private String faq_title;
    private String faq_content;
    private Date faq_date;

    public GetFaqBoardResponseDTO(final FaqBoard faqBoard) {
        this.faq_num = faqBoard.getFaqNum();
        this.faq_cate = faqBoard.getFaqCate();
        this.faq_title = faqBoard.getFaqTitle();
        this.faq_content = faqBoard.getFaqContent();
        this.faq_date = faqBoard.getFaqDate();
    }

}
