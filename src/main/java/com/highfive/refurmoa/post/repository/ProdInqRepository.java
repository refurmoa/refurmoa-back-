package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.ProdInquiry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProdInqRepository extends JpaRepository<ProdInquiry, Integer> {

    Page<ProdInquiry> findByBoardBoardNumOrderByProdInquiryNumDesc(int boardNum, Pageable pageable); // 상품 문의 글 목록 조회

    ProdInquiry findByProdInquiryNum(int prodInquiryNum);

}
