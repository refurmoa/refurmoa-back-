package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.post.dto.reponse.PaymentResponseDTO;
import com.highfive.refurmoa.post.dto.request.PaymentRequestDTO;
import org.springframework.data.domain.Page;

public interface PaymentService {
    public void getPaymentList(PaymentRequestDTO paymentRequestDTO);
    //    public Page<PaymentResponseDTO> getPaymentList(PaymentRequestDTO paymentRequestDTO);
}
