package com.highfive.refurmoa.user.service;

import com.highfive.refurmoa.entity.Bid;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Payment;
import com.highfive.refurmoa.post.dto.reponse.PaymentResponseDTO;
import com.highfive.refurmoa.post.dto.request.PaymentRequestDTO;
import com.highfive.refurmoa.post.repository.BidRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.PaymentRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final MemberRepository memberRepository;
    private final BoardRepository boardRepository;
    private final PaymentRepository paymentRepository;
    private final BidRepository bidRepository;

    @Override
    public void getPaymentList(PaymentRequestDTO paymentRequestDTO) {
        Pageable pageable = PageRequest.of(paymentRequestDTO.getPage(), paymentRequestDTO.getSize());
        Member member = memberRepository.findByMemberId(paymentRequestDTO.getMember_id());

//        Page<Payment> payment = paymentRepository.findByMemberId(member, pageable);
//        Page<Bid> bid = bidRepository
//                return Page 20
//        List<Payment> = payment.map(item -> {
//            return new PaymentResponseDTO(item);
//        });
    }
}
