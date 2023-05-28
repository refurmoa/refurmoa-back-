package com.highfive.refurmoa.post.controller;

import com.highfive.refurmoa.post.dto.PostDetailResponseDTO;
import com.highfive.refurmoa.post.dto.ProdInqListResponseDTO;
import com.highfive.refurmoa.post.service.PostDetailServiceImpl;
import com.highfive.refurmoa.post.dto.BidListResponseDTO;
import com.highfive.refurmoa.post.dto.ProdInqRequestDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/post/detail")
public class PostDetailController {

    private final PostDetailServiceImpl postDetailService;

    public PostDetailController(PostDetailServiceImpl postDetailService) {
        this.postDetailService = postDetailService;
    }


    // 판매 상세 정보 조회
    @PostMapping("/{boardNum}")
    public PostDetailResponseDTO getPostDetail(@PathVariable int boardNum, @RequestParam(required = false) String memberId) {
        return postDetailService.getPostDetail(boardNum, memberId);
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

}