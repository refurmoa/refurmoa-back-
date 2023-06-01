package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.AutoBid;
import com.highfive.refurmoa.post.dto.reponse.*;
import com.highfive.refurmoa.post.dto.request.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostDetailService {

    PostDetailResponseDTO getPostDetail(int boardNum, String memberId); // 판매 상세 정보 조회
    void deletePost(int boardNum); // 판매 글 삭제
    void insertBid(BidRequestDTO bidRequestDTO); // 입찰
    void insertAutoBid(BidRequestDTO bidRequestDTO); // 자동입찰
    Page<BidListResponseDTO> getListBid(int boardNum, Pageable pageable); // 입찰 목록 조회
    Page<ProdInqListResponseDTO> getListInq(int boardNum, Pageable pageable); // 상품 문의 글 목록 조회
    void insertProdInquiry(ProdInqRequestDTO prodInquiryDTO); // 상품 문의 글 등록
    void deleteProdInquiry(int prodInquiryNum); // 상품 문의 글 삭제
    void insertProdInqReply(ProdInqReplyRequestDTO prodInqReplyRequestDTO); // 상품 문의 댓글 등록

}