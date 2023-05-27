package com.highfive.refurmoa.cs.service;

import com.highfive.refurmoa.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FaqBoardService {
    public Page<FaqBoard> getFaqBoard(int faq_cate, Pageable pageable);
}
