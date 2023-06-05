package com.highfive.refurmoa.pay.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.pay.dto.request.PayDetailRequestDTO;
import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayDetailResponseDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.couponListDTO;
import com.highfive.refurmoa.pay.service.PayServiceImpl;
import com.highfive.refurmoa.prod.controller.ProductController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/pay")
public class PayController {

    private final PayServiceImpl payService;
    private final ProductController productController;

    // 결제(상품)정보 조회
    @GetMapping("/info")
    public PayInfoResponseDTO getPayInfo(@RequestParam("board_num") int boardNum, @RequestParam("sell_type") int sellType) {
        return payService.getPayInfo(boardNum, sellType);
    }

    // 회원 정보 조회
    @PostMapping("/user")
    public UserInfoResponseDTO getUserInfo(@RequestBody Member member) {
        return payService.getUserInfo(member.getMemberId());
    }

    // 결제
    @PostMapping("")
    public void insertPay(@RequestBody PayRequestDTO payRequestDTO) {
        payService.insertPay(payRequestDTO);
        productController.updateProdState(payRequestDTO.getProduct_code());
    }
    //회원 쿠폰 정보 조회
    @GetMapping("/coupon")
    public Page<couponListDTO> couponList(@RequestParam("id") String id, Pageable pageable) {
        return payService.couponList(id, pageable);
    }

    // 주문 상세 정보 조회
    @PostMapping("/detail")
    public PayDetailResponseDTO getPayDetail(@RequestBody PayDetailRequestDTO payDetailRequestDTO) {
        return payService.getPayDetail(payDetailRequestDTO);
    }
}
