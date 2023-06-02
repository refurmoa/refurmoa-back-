package com.highfive.refurmoa.user.controller;

import com.highfive.refurmoa.post.dto.reponse.PaymentResponseDTO;
import com.highfive.refurmoa.post.dto.request.PaymentRequestDTO;
import com.highfive.refurmoa.user.service.PaymentServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentServiceImpl paymentServiceImpl;

    // 결제 내역 조회
    @PostMapping("/user/payment")
    public void getPaymentList(@RequestBody PaymentRequestDTO paymentRequestDTO) {
        paymentServiceImpl.getPaymentList(paymentRequestDTO);
//        return paymentServiceImpl.getPaymentList(paymentRequestDTO);
    }

}
