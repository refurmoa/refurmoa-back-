package com.highfive.refurmoa.cs.service;

import com.highfive.refurmoa.cs.repository.FaqBoardRepository;
import com.highfive.refurmoa.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class FaqBoardServiceImpl implements FaqBoardService {

    private FaqBoardRepository repository;

    public FaqBoardServiceImpl(FaqBoardRepository repository) {
        this.repository = repository;
    }

    @Override
    public Page<FaqBoard> getFaqBoard(int faq_cate, Pageable pageable) {
        if (faq_cate == 0) {
            return repository.findAll(pageable);
        } else {
            return repository.findByFaqCate(faq_cate, pageable);
        }
    }
}

