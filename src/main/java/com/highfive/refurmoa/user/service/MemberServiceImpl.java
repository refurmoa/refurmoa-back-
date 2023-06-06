package com.highfive.refurmoa.user.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Mile;
import com.highfive.refurmoa.entity.Userlike;
import com.highfive.refurmoa.pay.repository.PaymentRepository;
import com.highfive.refurmoa.post.dto.reponse.MyListDTO;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.UserlikeRepository;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;
import com.highfive.refurmoa.user.DTO.reponse.MemberInfoDTO;
import com.highfive.refurmoa.user.DTO.reponse.MembershipDTO;
import com.highfive.refurmoa.user.DTO.reponse.couponDTO;
import com.highfive.refurmoa.user.DTO.reponse.historyDTO;
import com.highfive.refurmoa.user.DTO.reponse.memberGradeDTO;
import com.highfive.refurmoa.user.DTO.reponse.mileDTO;
import com.highfive.refurmoa.user.repository.CouponRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import com.highfive.refurmoa.user.repository.MileRepository;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository repository;
    private CouponRepository couponrepository;
    private MileRepository milerepository;
    private BoardRepository boardRepository;
    private UserlikeRepository userlikeRepository;
    private PaymentRepository paymentRepository;
    public MemberServiceImpl(MemberRepository repository,CouponRepository couponrepository,PaymentRepository paymentRepository,
    		MileRepository milerepository,BoardRepository boardRepository,UserlikeRepository userlikeRepository) {
        this.repository = repository;
        this.couponrepository=couponrepository;
        this.milerepository=milerepository	;
        this.boardRepository = boardRepository;
        this.userlikeRepository=userlikeRepository;
        this.paymentRepository= paymentRepository;
    }

    @Override // 로그인
    public int login(String member_id, String password) {
        Member member = repository.findByMemberIdAndPassword(member_id, password);
        if (member != null) return 1;
        else return 0;
    }

    @Override // ID 찾기
    public String findID(String name, String phone) {
        Member member = repository.findByNameAndPhone(name, phone);
        if (member != null) return member.getMemberId();
        else return null;
    }

    @Override // PW 찾기
    public String findPW(String member_id, String name, String phone) {
        Member member = repository.findByMemberIdAndNameAndPhone(member_id, name, phone);
        if (member != null) return member.getPassword();
        else return null;
    }

    @Override // 회원가입
    public void insertMember(Member member) {
        repository.save(member);
    }

    @Override // ID 중복 검사
    public long countMemberId(String memberId) {
        return repository.countByMemberId(memberId);
    }
    

 	
 	//	회원주소검색
 	@Override
 	public String userLocationInfo(String memberId, boolean acceptLocation) {
 		Member member = repository.findAllByMemberIdAndAcceptLocation(memberId, acceptLocation);
 		if (member != null) return member.getAddress();
         else return "0";
 	}
 	
// 	// 회원정보 불러오기
// 	@Override
// 	public List<AdminUserListResponseDTO> listAdminMember() {
// 		return (List<AdminUserListResponseDTO>)repository.listAdminMember();
// 	}
 	
 // 회원정보 불러오기
  	@Override
  	public Slice<AdminUserListResponseDTO> listAdminMember(Pageable pageable) {
  		return (Slice<AdminUserListResponseDTO>)repository.listAdminMember(pageable);
  	}
 	
 	// admin 회원 검색
 	@Override
 	public List<AdminUserListResponseDTO> searchAdminMember(String memberId){
 		return (List<AdminUserListResponseDTO>)repository.findByMemberIdOrName(memberId);
 	}
 	
 	//마이페이지 첫번째 줄
 	@Override
 	public MemberInfoDTO memberInfo(String id) {
 		Member mem=repository.findByMemberId(id);
 		int order=paymentRepository.payment(mem.getMemberId());//결제 배송
 		int bid=repository.bidCount(mem.getMemberId());//입찰 내역
 		int uselike=userlikeRepository.uselike(mem.getMemberId());//찜한 상품
 		return new MemberInfoDTO(mem,order,bid,uselike);
 				
 	}
 	//마이페이지 두번째 줄
 	@Override
 	 public MembershipDTO membership(String id) {
 		Member mem=repository.findByMemberId(id);
 		Integer num=0;
 		if(paymentRepository.payamount(id)!=null)num=paymentRepository.payamount(id);
 		memberGradeDTO grade= new memberGradeDTO(mem.getGrade(),num);
 		List<Mile> memmile=milerepository.mileList(mem.getMemberId());//마일리지 리스트
 		List<historyDTO> his=new ArrayList<>();
 		for(Mile tmp:memmile) {
 			his.add(new historyDTO(tmp.getContent(),tmp.getPoint()));
 		}
 		Integer milege=0;
 		if(milerepository.mileAmount(mem.getMemberId())!=null)milege=milerepository.mileAmount(mem.getMemberId());
 		mileDTO mile =new mileDTO(milege,his);
 		
 		List<Coupon> coupon=couponrepository.memCoupon(mem.getMemberId(),new Date());
 		List<couponDTO> memcou=new ArrayList<>();
 		for(Coupon tmp:coupon) {
 			memcou.add(new couponDTO(tmp.getCouponName(),tmp.getSalePrice()));
 		}
 		return new MembershipDTO(grade,mile,memcou);
 	}
 	//마이페이지 찜 상품 목록
 	@Override
 	public List<MyListDTO> bookmarkData(String id,String search){
 		
 		Member mem=repository.findByMemberId(id);
 		List<Userlike> like=userlikeRepository.findByMemberMemberId(id);
 		List<MyListDTO> list=new ArrayList<>();
 		for(Userlike tmp:like) {
 			
 			int boardNum=tmp.getBoard().getBoardNum();
 			Board board=boardRepository.findByBoardNum(boardNum);
 			if(board.getProduct().getProdName().contains(search)) {
 				if(!board.isDeleteCheck())list.add(new MyListDTO(board));
 			}
 		}
		return list;	
 	}
}