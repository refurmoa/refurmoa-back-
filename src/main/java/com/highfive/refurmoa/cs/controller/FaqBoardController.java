package com.highfive.refurmoa.cs.controller;

import com.highfive.refurmoa.cs.dto.response.GetFaqBoardResponseDTO;
import com.highfive.refurmoa.cs.service.FaqBoardServiceImpl;
import com.highfive.refurmoa.entity.FaqBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class FaqBoardController {

    private final FaqBoardServiceImpl faqBoardServiceImpl;
    @GetMapping("/cs/faq")
    public Page<GetFaqBoardResponseDTO> getFaqBoard(int faq_cate, Pageable pageable) {
        Page<FaqBoard> faqBoard = faqBoardServiceImpl.getFaqBoard(faq_cate, pageable);
        Page<GetFaqBoardResponseDTO> getFaqBoardResponseDTOS = faqBoard.map(GetFaqBoardResponseDTO::new);
        return getFaqBoardResponseDTOS;
    }
}
