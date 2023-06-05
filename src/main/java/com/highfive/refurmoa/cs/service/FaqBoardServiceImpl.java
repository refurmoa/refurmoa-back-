package com.highfive.refurmoa.cs.service;

import com.highfive.refurmoa.cs.dto.request.FaqBoardUpdateDTO;
import com.highfive.refurmoa.cs.dto.request.FaqBoardWriteDTO;
import com.highfive.refurmoa.cs.repository.FaqBoardRepository;
import com.highfive.refurmoa.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class FaqBoardServiceImpl implements FaqBoardService {

    private FaqBoardRepository repository;

    public FaqBoardServiceImpl(FaqBoardRepository repository) {
        this.repository = repository;
    }

    // Faq 목록 조회
    @Override
    public Page<FaqBoard> getFaqBoard(int faq_cate, Pageable pageable) {
        if (faq_cate == 0) {
            return repository.findAll(pageable);
        } else {
            return repository.findByFaqCate(faq_cate, pageable);
        }
    }

    // Faq 목록 검색(제목)
    @Override
    public Page<FaqBoard> searchFaqBoard(String search, Pageable pageable) {
        return repository.findByFaqTitleContaining(search, pageable);
    }

    // Faq 삭제
    @Override
    public int deleteFaqBoard(int faq_num) {
        repository.deleteByFaqNum(faq_num);
        return 1;
    }

    // Faq 작성
    @Override
    public int writeFaqBoard(FaqBoardWriteDTO faqBoardWriteDTO) {
        FaqBoard faqBoard = new FaqBoard(faqBoardWriteDTO);
        try {
            repository.save(faqBoard);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }

    // Faq 수정
    @Override
    public int updateFaqBoard(FaqBoardUpdateDTO faqBoardUpdateDTO) {
        FaqBoard faqBoard = repository.findByFaqNum(faqBoardUpdateDTO.getFaq_num());
        faqBoard.setFaqCate(faqBoardUpdateDTO.getFaq_cate());
        faqBoard.setFaqTitle(faqBoardUpdateDTO.getFaq_title());
        faqBoard.setFaqContent(faqBoardUpdateDTO.getFaq_content());
        try {
            repository.save(faqBoard);
            return 1;
        } catch (Exception e) {
            return 0;
        }
    }
    //Faq 조회수 증가
    @Override
    public void readcount(int faq_num) {
    	repository.findByFaqNumAndUpdatePlusReadCount(faq_num);
    	
    	
    }

}

