package com.highfive.refurmoa.pay.service;

import com.highfive.refurmoa.pay.dto.request.PayDetailRequestDTO;
import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayDetailResponseDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.couponListDTO;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

public interface PayService {

    PayInfoResponseDTO getPayInfo(int boardNum, int sellType); // 결제(상품)정보 조회
    UserInfoResponseDTO getUserInfo(String memberId); // 회원 정보 조회
    void insertPay(@RequestBody PayRequestDTO payRequestDTO); // 결제
    public Page<couponListDTO> couponList(String id, Pageable pageable);//쿠폰 조회
    void insertPay(PayRequestDTO payRequestDTO); // 결제
    PayDetailResponseDTO getPayDetail(PayDetailRequestDTO payDetailRequestDTO); // 주문 상세 정보 조회

}
