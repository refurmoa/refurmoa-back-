package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.user.DTO.request.ChangeToConfirmRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListPeriodRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListRequestDTO;
import com.highfive.refurmoa.user.DTO.reponse.PaymentListResponseDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListSearchRequestDTO;
import org.springframework.data.domain.Page;

public interface PaymentService {
    // 결제 내역 조회
    public Page<PaymentListResponseDTO> getPaymentList(PaymentListRequestDTO paymentListRequestDTO);
    // 결제 내역 기간 조회
    public Page<PaymentListResponseDTO> getPaymentListPeriod(PaymentListPeriodRequestDTO paymentListPeriodRequestDTO);
    // 결제 내역 검색
    Page<PaymentListResponseDTO> getPaymentListSearch(PaymentListSearchRequestDTO paymentListSearchRequestDTO);
    // 구매 확정
    void changeToConfirm(ChangeToConfirmRequestDTO changeToConfirmRequestDTO);

}
