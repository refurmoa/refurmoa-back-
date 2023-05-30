package com.highfive.refurmoa.post.controller;

import com.highfive.refurmoa.entity.AutoBid;
import com.highfive.refurmoa.entity.Userlike;
import com.highfive.refurmoa.post.dto.reponse.*;
import com.highfive.refurmoa.post.dto.request.*;
import com.highfive.refurmoa.post.service.PostDetailServiceImpl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/detail")
public class PostDetailController {

    private final PostDetailServiceImpl postDetailService;

    public PostDetailController(PostDetailServiceImpl postDetailService) { this.postDetailService = postDetailService; }


    // 판매 상세 정보 조회
    @PostMapping("")
    public PostDetailResponseDTO getPostDetail(@RequestBody UserlikeRequestDTO requestDTO) {
        return postDetailService.getPostDetail(requestDTO.getBoardNum(), requestDTO.getMemberId());
    }

    // 판매 글 삭제
    @GetMapping("/delete")
    public void deletePost(@RequestParam(value = "board_num") int boardNum) {
        postDetailService.deletePost(boardNum);
    }

    // 입찰
    @PostMapping("/bid/insert")
    public void insertBid(@RequestBody BidRequestDTO bidRequestDTO) {
        postDetailService.insertBid(bidRequestDTO);
    }

    // 자동입찰
    @PostMapping("/bid/auto")
    public void insertAutoBid(@RequestBody BidRequestDTO bidRequestDTO) {
        postDetailService.insertAutoBid(bidRequestDTO);
    }

    // 입찰 목록 조회
    @GetMapping("/bid")
    public Page<BidListResponseDTO> getListBid(@RequestParam(value = "board_num") int boardNum, Pageable pageable) {
        return postDetailService.getListBid(boardNum, pageable);
    }

    // 상품 문의 글 목록 조회
    @GetMapping("/inquiry")
    public Page<ProdInqListResponseDTO> getListInq(@RequestParam(value = "board_num") int boardNum, Pageable pageable) {
        return postDetailService.getListInq(boardNum, pageable);
    }

    // 상품 문의 글 등록
    @PostMapping("/inquiry/insert")
    public void insertProdInquiry(@RequestBody ProdInqRequestDTO prodInquiryDTO) {
        postDetailService.insertProdInquiry(prodInquiryDTO);
    }

    // 상품 문의 글 삭제
    @GetMapping("/inquiry/delete")
    public void deleteProdInquiry(@RequestParam(value = "prod_inquiry_num") int prodInquiryNum) {
        postDetailService.deleteProdInquiry(prodInquiryNum);
    }

    // 상품 문의 댓글 등록
    @PostMapping("/inquiry/answer/insert")
    public void insertProdInqReply(@RequestBody ProdInqReplyRequestDTO prodInqReplyRequestDTO) {
        postDetailService.insertProdInqReply(prodInqReplyRequestDTO);
    }

}