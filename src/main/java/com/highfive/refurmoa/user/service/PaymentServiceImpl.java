package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.entity.Delivery;
import com.highfive.refurmoa.entity.Payment;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.repository.ProductRepository;
import com.highfive.refurmoa.user.DTO.request.ChangeToConfirmRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListPeriodRequestDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListRequestDTO;
import com.highfive.refurmoa.post.repository.PaymentRepository;
import com.highfive.refurmoa.user.DTO.reponse.PaymentListResponseDTO;
import com.highfive.refurmoa.user.DTO.request.PaymentListSearchRequestDTO;
import com.highfive.refurmoa.pay.repository.DeliveryRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    // 결제 내역 조회
    @Override
    public Page<PaymentListResponseDTO> getPaymentList(PaymentListRequestDTO paymentListRequestDTO) {
        Pageable pageable = PageRequest.of(paymentListRequestDTO.getPage(), paymentListRequestDTO.getSize());
        Page<Payment> payment = paymentRepository.findByMemberMemberId(paymentListRequestDTO.getMember_id(), pageable);
        return payment.map(payment_item -> {
            Delivery delivery = deliveryRepository.findByProductProductCode(payment_item.getProduct().getProductCode());
            return new PaymentListResponseDTO(payment_item, delivery);
        });
    }

    // 결제 내역 기간 조회
    @Override
    public Page<PaymentListResponseDTO> getPaymentListPeriod(PaymentListPeriodRequestDTO paymentListPeriodRequestDTO) {
        Pageable pageable = PageRequest.of(paymentListPeriodRequestDTO.getPage(), paymentListPeriodRequestDTO.getSize());
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        String startDate = paymentListPeriodRequestDTO.getDate_start();
        String endDate = paymentListPeriodRequestDTO.getDate_end();
        Date startDateD = null;
        Date endDateD = null;

        try {
            startDateD = dateformat.parse(startDate);
            endDateD = dateformat.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Page<Payment> payment = paymentRepository.findByMemberIdAndPeriod(paymentListPeriodRequestDTO.getMember_id(), startDateD, endDateD, pageable);
        return payment.map(payment_item -> {
            Delivery delivery = deliveryRepository.findByProductProductCode(payment_item.getProduct().getProductCode());
            return new PaymentListResponseDTO(payment_item, delivery);
        });
    }

    // 결제 내역 검색
    public Page<PaymentListResponseDTO> getPaymentListSearch(PaymentListSearchRequestDTO paymentListSearchRequestDTO) {
        Pageable pageable = PageRequest.of(paymentListSearchRequestDTO.getPage(), paymentListSearchRequestDTO.getSize());
        Page<Payment> payment = paymentRepository.findByMemberIdAndSearch(paymentListSearchRequestDTO.getMember_id(), paymentListSearchRequestDTO.getSearch(), pageable);
        return payment.map(payment_item -> {
            Delivery delivery = deliveryRepository.findByProductProductCode(payment_item.getProduct().getProductCode());
            return new PaymentListResponseDTO(payment_item, delivery);
        });
    }

    // 구매 확정
    public void changeToConfirm(ChangeToConfirmRequestDTO changeToConfirmRequestDTO) {
        // 상품현황 변경
        Product product = productRepository.findByProductCode(changeToConfirmRequestDTO.getProduct_code());

        // 회원 마일리지 적립
        Payment payment = paymentRepository.findByProductProductCode(product.getProductCode());
        memberRepository.findByMemberIdAndUpdateMile(changeToConfirmRequestDTO.getMember_id(), payment.getPayPrice()/100);
    }
}
