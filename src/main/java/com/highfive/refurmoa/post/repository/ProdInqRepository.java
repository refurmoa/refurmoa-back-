package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Bid;
import com.highfive.refurmoa.entity.ProdInquiry;

import com.highfive.refurmoa.post.dto.ProdInqListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProdInqRepository extends JpaRepository<ProdInquiry, Integer> {

    Page<ProdInquiry> findByBoardBoardNumOrderByProdInquiryNumDesc(int boardNum, Pageable pageable);

    ProdInquiry findByProdInquiryNum(int prodInquiryNum);
}
