package com.highfive.refurmoa.pay.service;

import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import org.springframework.web.bind.annotation.RequestBody;

public interface PayService {

    PayInfoResponseDTO getPayInfo(int boardNum, int sellType); // 결제(상품)정보 조회
    UserInfoResponseDTO getUserInfo(String memberId); // 회원 정보 조회
    void insertPay(@RequestBody PayRequestDTO payRequestDTO); // 결제

}
