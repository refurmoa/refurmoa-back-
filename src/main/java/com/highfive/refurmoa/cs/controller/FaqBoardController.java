package com.highfive.refurmoa.cs.controller;

import com.highfive.refurmoa.cs.dto.request.FaqBoardUpdateDTO;
import com.highfive.refurmoa.cs.dto.request.FaqBoardWriteDTO;
import com.highfive.refurmoa.cs.dto.response.GetFaqBoardResponseDTO;
import com.highfive.refurmoa.cs.service.FaqBoardServiceImpl;
import com.highfive.refurmoa.entity.FaqBoard;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class FaqBoardController {

    private final FaqBoardServiceImpl faqBoardServiceImpl;

    // Faq 목록 조회
    @GetMapping("/cs/faq")
    public Page<GetFaqBoardResponseDTO> getFaqBoard(int faq_cate, Pageable pageable) {
        Page<FaqBoard> faqBoard = faqBoardServiceImpl.getFaqBoard(faq_cate, pageable);
        Page<GetFaqBoardResponseDTO> getFaqBoardResponseDTOS = faqBoard.map(GetFaqBoardResponseDTO::new);
        return getFaqBoardResponseDTOS;
    }

    // Faq 목록 검색(제목)
    @GetMapping("/cs/faq/search")
    public Page<GetFaqBoardResponseDTO> searchFaqBoard(String search, Pageable pageable) {
        Page<FaqBoard> faqBoard = faqBoardServiceImpl.searchFaqBoard(search, pageable);
        Page<GetFaqBoardResponseDTO> getFaqBoardResponseDTOS = faqBoard.map(GetFaqBoardResponseDTO::new);
        return getFaqBoardResponseDTOS;
    }

    // Faq 삭제
    @GetMapping("/cs/faq/delete")
    public int deleteFaqBoard(@RequestParam int faq_num) {
        return faqBoardServiceImpl.deleteFaqBoard(faq_num);
    }

    // Faq 작성
    @PostMapping("/cs/faq/write")
    public int writeFaqBoard(@RequestBody FaqBoardWriteDTO faqBoardWriteDTO) {
        return faqBoardServiceImpl.writeFaqBoard(faqBoardWriteDTO);
    }

    // Faq 수정
    @PostMapping("/cs/faq/update")
    public int updateFaqBoard(@RequestBody FaqBoardUpdateDTO faqBoardUpdateDTO) {
        return faqBoardServiceImpl.updateFaqBoard(faqBoardUpdateDTO);
    }
}
