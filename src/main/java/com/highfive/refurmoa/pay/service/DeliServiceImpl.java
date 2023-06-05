package com.highfive.refurmoa.pay.service;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Delivery;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.pay.dto.response.DeliResponseDTO;
import com.highfive.refurmoa.pay.dto.response.couponListDTO;
import com.highfive.refurmoa.pay.repository.DeliveryRepository;
import com.highfive.refurmoa.pay.repository.PayRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.user.repository.CouponRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import com.highfive.refurmoa.user.repository.MileRepository;

@Service
public class DeliServiceImpl implements DeliService {
	private final PayRepository payRepository;
    private final DeliveryRepository deliveryRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;
    private final CouponRepository couponRepository;
    private final MileRepository mileRepository;

    public DeliServiceImpl(PayRepository payRepository, DeliveryRepository deliveryRepository, BoardRepository boardRepository,
                          MemberRepository memberRepository, CouponRepository couponRepository, MileRepository mileRepository) {
        this.payRepository = payRepository;
        this.deliveryRepository = deliveryRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
        this.couponRepository = couponRepository;
        this.mileRepository = mileRepository;
    }
    
    @Override
    public Page<DeliResponseDTO> adminOrder(String search,Pageable pagealbe){
    	Page<Delivery> deli = deliveryRepository.findOrder(search,pagealbe);
    	
		Page<DeliResponseDTO> Page =deli.map(tmp->{
			
			String pay_num=tmp.getPayment().getPayNum();
			int product_code=tmp.getProduct().getProductCode();
			int board_num=tmp.getPayment().getBoard().getBoardNum();
			String receipt_name=tmp.getReceiptName();
			String receipt_phone=tmp.getReceiptPhone();
			String deli_num=tmp.getDeliNum();
			
			int state=0;
			if(tmp.getDeliNum()==null)state=1;
			else {
				if(tmp.getProduct().getProdState()==4)state=3;
				else state=2;
			}
			
			Date pay_date=tmp.getPayment().getPayDate();
			
			return new DeliResponseDTO(pay_num,product_code,board_num,receipt_name,receipt_phone,deli_num,state,pay_date);
		});
		return Page;
    	
    }	
   
    
}
