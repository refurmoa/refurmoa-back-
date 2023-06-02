package com.highfive.refurmoa.user.service;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;

import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;
import com.highfive.refurmoa.user.DTO.reponse.MemberInfoDTO;
import com.highfive.refurmoa.user.repository.MemberRepository;

@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository repository;
    public MemberServiceImpl(MemberRepository repository) {
        this.repository = repository;
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
}