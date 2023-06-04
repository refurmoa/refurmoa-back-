package com.highfive.refurmoa.pay.service;

import com.highfive.refurmoa.entity.*;
import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.repository.DeliveryRepository;
import com.highfive.refurmoa.pay.repository.PayRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.user.repository.CouponRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import com.highfive.refurmoa.user.repository.MileRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;
    private final DeliveryRepository deliveryRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MileRepository mileRepository;

    public PayServiceImpl(PayRepository payRepository, DeliveryRepository deliveryRepository, BoardRepository boardRepository,
                          MemberRepository memberRepository, CouponRepository couponRepository, MileRepository mileRepository) {
        this.payRepository = payRepository;
        this.deliveryRepository = deliveryRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.couponRepository = couponRepository;
        this.mileRepository = mileRepository;
    }

    @Override // 결제(상품)정보 조회
    public PayInfoResponseDTO getPayInfo(int boardNum, int sellType) {
        Board board = boardRepository.findByBoardNumAndDeleteCheckFalse(boardNum);
        return new PayInfoResponseDTO(board, sellType);
    }

    @Override // 회원 정보 조회
    public UserInfoResponseDTO getUserInfo(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return new UserInfoResponseDTO(member);
    }

    @Override // 결제
    public void insertPay(@RequestBody PayRequestDTO payRequestDTO) {
        if (payRequestDTO.getMile_use() != 0) { // 마일리지 사용
            Member member = memberRepository.findByMemberId(payRequestDTO.getMember_id());
            int memberMile = member.getMile();
            member.setMile(memberMile - payRequestDTO.getMile_use());
            Mile mile = new Mile(null, member, "상품 결제", -payRequestDTO.getMile_use());
            mileRepository.save(mile);
        }
        if (payRequestDTO.getCoupon_num() != null) { // 쿠폰 사용
            couponRepository.useCoupon(payRequestDTO.getCoupon_num());
        }
        Payment payment = new Payment(payRequestDTO);
        payRepository.save(payment);
        Delivery delivery = new Delivery(payRequestDTO);
        deliveryRepository.save(delivery);
    }

}
