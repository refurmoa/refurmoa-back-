package com.highfive.refurmoa.cs.repository;

import com.highfive.refurmoa.entity.FaqBoard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FaqBoardRepository extends JpaRepository<FaqBoard, Integer> {
    Page<FaqBoard> findAll(Pageable pageable);
    Page<FaqBoard> findByFaqCate(int faq_cate,Pageable pageable);
}
