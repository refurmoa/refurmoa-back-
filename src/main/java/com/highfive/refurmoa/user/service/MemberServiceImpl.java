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
    public MemberServiceImpl(MemberRepository repository,CouponRepository couponrepository, MileRepository milerepository,BoardRepository boardRepository,UserlikeRepository userlikeRepository) {
        this.repository = repository;
        this.couponrepository=couponrepository;
        this.milerepository=milerepository	;
        this.boardRepository = boardRepository;
        this.userlikeRepository=userlikeRepository;
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
    
 // 회원정보 불러오기
 	@Override
 	public List<Member> listMember(String memberId) {
 		return (List<Member>)repository.findAllByMemberId(memberId);
 	}
 	
 	// 회원탈퇴
 	@Override
 	public void deleteMember(String memberId) {
 		repository.deleteById(memberId);
 	}
 	
 	// 회원정보 수정
 	@Override
 	public Member updateMember(Member member) {
 		return repository.save(member);
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
 	@Override
 	public MemberInfoDTO memberInfo(String id) {
 		Member mem=repository.findByMemberId(id);
 		int order=repository.payment(mem.getMemberId());
 		int bid=repository.bidCount(mem.getMemberId());
 		int uselike=repository.uselike(mem.getMemberId());
 		return new MemberInfoDTO(mem,order,bid,uselike);
 				
 	}
 	@Override
 	 public MembershipDTO membership(String id) {
 		Member mem=repository.findByMemberId(id);
 		memberGradeDTO grade= new memberGradeDTO(mem.getGrade(),repository.payamount(id));
 		List<Mile> memmile=milerepository.mileList(mem.getMemberId());
 		List<historyDTO> his=new ArrayList<>();
 		for(Mile tmp:memmile) {
 			his.add(new historyDTO(tmp.getContent(),tmp.getPoint()));
 		}
 		mileDTO mile =new mileDTO(repository.mileAmount(mem.getMemberId()),his);
 		
 		List<Coupon> coupon=couponrepository.memCoupon(mem.getMemberId(),new Date());
 		List<couponDTO> memcou=new ArrayList<>();
 		for(Coupon tmp:coupon) {
 			memcou.add(new couponDTO(tmp.getCouponName(),tmp.getSalePrice()));
 		}
 		return new MembershipDTO(grade,mile,memcou);
 	}
 	@Override
 	public List<MyListDTO> bookmarkData(String id,String search){
 		
 		Member mem=repository.findByMemberId(id);
 		List<Userlike> like=userlikeRepository.findByMemberMemberId(id);
 		List<MyListDTO> list=new ArrayList<>();
 		for(Userlike tmp:like) {
 			
 			int boardNum=tmp.getBoard().getBoardNum();
 			Board board=boardRepository.findByBoardNum(boardNum);
 			if(board.getProduct().getProdName().contains(search))list.add(new MyListDTO(board));
 		}
		return list;	
 	}
}