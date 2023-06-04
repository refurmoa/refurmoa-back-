package com.highfive.refurmoa.pay.controller;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.service.PayServiceImpl;
import com.highfive.refurmoa.prod.controller.ProductController;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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

}
