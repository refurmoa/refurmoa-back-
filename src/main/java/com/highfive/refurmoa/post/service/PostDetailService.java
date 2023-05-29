package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.post.dto.*;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostDetailService {

    PostDetailResponseDTO getPostDetail(int boardNum, String memberId); // 판매 상세 정보 조회
    int deletePost(int boardNum); // 판매 글 삭제
    Page<BidListResponseDTO> getListBid(int boardNum, Pageable pageable); // 입찰 목록 조회
    Page<ProdInqListResponseDTO> getListInq(int boardNum, Pageable pageable); // 상품 문의 글 목록 조회
    int insertProdInquiry(ProdInqRequestDTO prodInquiryDTO); // 상품 문의 글 등록
    int deleteProdInquiry(int prodInquiryNum); // 상품 문의 글 삭제
    int insertProdInqReply(ProdInqReplyRequestDTO prodInqReplyRequestDTO); // 상품 문의 댓글 등록

}