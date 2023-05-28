package com.highfive.refurmoa.post.controller;

import com.highfive.refurmoa.post.dto.*;
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
    @PostMapping("/{boardNum}")
    public PostDetailResponseDTO getPostDetail(@PathVariable int boardNum, @RequestParam(required = false) String memberId) {
        return postDetailService.getPostDetail(boardNum, memberId);
    }

    // 판매 글 삭제
    @GetMapping("/delete")
    public int deletePost(@RequestParam(value = "board_num") int boardNum) {
        return postDetailService.deletePost(boardNum);
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
    public int insertProdInquiry(@RequestBody ProdInqRequestDTO prodInquiryDTO) {
        return postDetailService.insertProdInquiry(prodInquiryDTO);
    }

    // 상품 문의 글 삭제
    @GetMapping("/inquiry/delete")
    public int deleteProdInquiry(@RequestParam(value = "prod_inquiry_num") int prodInquiryNum) {
        return postDetailService.deleteProdInquiry(prodInquiryNum);
    }

    // 상품 문의 댓글 등록
    @PostMapping("/inquiry/answer/insert")
    public int insertProdInqReply(@RequestBody ProdInqReplyRequestDTO prodInqReplyRequestDTO) {
        return postDetailService.insertProdInqReply(prodInqReplyRequestDTO);
    }

}