package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.ProdInquiryReply;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdInqReplyRepository extends JpaRepository<ProdInquiryReply, Integer> {

    ProdInquiryReply findByProdInquiryProdInquiryNum(int prodInquiryNum); // 상품 문의 답변 조회
    
}
