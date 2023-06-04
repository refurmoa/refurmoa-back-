package com.highfive.refurmoa.user.controller;

import com.highfive.refurmoa.user.DTO.request.ChangeToConfirmRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListPeriodRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListRequestDTO;
import com.highfive.refurmoa.user.DTO.reponse.PaymentListResponseDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListSearchRequestDTO;
import com.highfive.refurmoa.user.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    // 결제 내역 조회
    @PostMapping("/user/payment")
    public Page<PaymentListResponseDTO> getPaymentList(@RequestBody PaymentListRequestDTO paymentListRequestDTO) {
        return paymentServiceImpl.getPaymentList(paymentListRequestDTO);
    }

    // 결제 내역 기간 조회
    @PostMapping("/mypage/payment/period")
    public Page<PaymentListResponseDTO> getPaymentListPeriod(@RequestBody PaymentListPeriodRequestDTO paymentListPeriodRequestDTO) {
        return paymentServiceImpl.getPaymentListPeriod(paymentListPeriodRequestDTO);
    }

    // 결제 내역 검색
    @PostMapping("/user/payment/search")
    public Page<PaymentListResponseDTO> getPaymentListSearch(@RequestBody PaymentListSearchRequestDTO paymentListSearchRequestDTO) {
        return paymentServiceImpl.getPaymentListSearch(paymentListSearchRequestDTO);
    }

    // 구매 확정
    @PostMapping("/prod/change")
    public void changeToConfirm(@RequestBody ChangeToConfirmRequestDTO changeToConfirmRequestDTO) {
        paymentServiceImpl.changeToConfirm(changeToConfirmRequestDTO);
    }

}
