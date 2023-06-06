package com.highfive.refurmoa.pay.service;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Delivery;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Mile;
import com.highfive.refurmoa.entity.Payment;
import com.highfive.refurmoa.pay.dto.request.PayDetailRequestDTO;
import com.highfive.refurmoa.pay.dto.request.PayRequestDTO;
import com.highfive.refurmoa.pay.dto.response.PayDetailResponseDTO;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.couponListDTO;
import com.highfive.refurmoa.pay.repository.DeliveryRepository;
import com.highfive.refurmoa.pay.repository.PaymentRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.user.repository.CouponRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import com.highfive.refurmoa.user.repository.MileRepository;

@Service
public class PayServiceImpl implements PayService {

    private final PaymentRepository paymentRepository;
    private final DeliveryRepository deliveryRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MileRepository mileRepository;

    public PayServiceImpl(PaymentRepository paymentRepository, DeliveryRepository deliveryRepository, BoardRepository boardRepository,
                          MemberRepository memberRepository, CouponRepository couponRepository, MileRepository mileRepository) {
        this.paymentRepository = paymentRepository;
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
    public void insertPay(PayRequestDTO payRequestDTO) {
        if (payRequestDTO.getMile_use() != 0) { // 마일리지 사용
            Member member = memberRepository.findByMemberId(payRequestDTO.getMember_id());
            int memberMile = member.getMile();
            member.setMile(memberMile - payRequestDTO.getMile_use());
            Mile mile = new Mile(null, member, "상품 결제", -payRequestDTO.getMile_use());
            mileRepository.save(mile);
        }
        if (payRequestDTO.getCoupon_num() != 0) { // 쿠폰 사용
            Date today = new Date();
            couponRepository.useCoupon(payRequestDTO.getCoupon_num(), today);
        }
        Payment payment = new Payment(payRequestDTO);
        paymentRepository.save(payment);
        Delivery delivery = new Delivery(payRequestDTO);
        deliveryRepository.save(delivery);
    }
    @Override 
    public Page<couponListDTO> couponList(String id,Pageable pageable){
    	Member mem=memberRepository.findByMemberId(id);
    	Page<Coupon> tmp=couponRepository.findCouponList(mem,new Date(),pageable);
		return tmp.map(couponListDTO::new);
    }

    // 주문 상세 정보 조회
    public PayDetailResponseDTO getPayDetail(PayDetailRequestDTO payDetailRequestDTO) {
        Payment payment = paymentRepository.findByBoardBoardNum(payDetailRequestDTO.getBoard_num());
        Delivery delivery = deliveryRepository.findByProductProductCode(payment.getProduct().getProductCode());
        Optional<Coupon> coupon = Optional.of(new Coupon());
        if (payment.getCouponNum() != null) {
            coupon = couponRepository.findById(payment.getCouponNum());
        }
        return new PayDetailResponseDTO(payment, delivery, coupon);
    }

	@Override
	public void canclePay(int payNum, int productCode) {
		paymentRepository.canclePay(payNum, productCode);
	}
    
 
}
