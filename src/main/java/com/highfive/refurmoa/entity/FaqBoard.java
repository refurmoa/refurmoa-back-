package com.highfive.refurmoa.entity;

import com.highfive.refurmoa.cs.dto.request.FaqBoardWriteDTO;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "faq_board")
public class FaqBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "faq_num", nullable = false)
    private int faqNum;

    @Column(name = "faq_cate", nullable = false)
    private int faqCate;

    @Column(name = "faq_title", nullable = false, length = 20)
    private String faqTitle;

    @Column(name = "faq_content", nullable = false, length = 500)
    private String faqContent;

    @Column(name = "faq_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date faqDate;

    public FaqBoard(FaqBoardWriteDTO faqBoardWriteDTO) {
        this.faqCate = faqBoardWriteDTO.getFaq_cate();
        this.faqTitle = faqBoardWriteDTO.getFaq_title();
        this.faqContent = faqBoardWriteDTO.getFaq_content();
        this.faqDate = faqBoardWriteDTO.getFaq_date();
    }

}
