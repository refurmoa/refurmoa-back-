package com.highfive.refurmoa.user.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Coupon;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.entity.Mile;
import com.highfive.refurmoa.user.DTO.reponse.AdminUserListResponseDTO;

public interface MemberRepository extends JpaRepository<Member, String> {

	Member findByMemberIdAndPassword(String member_id, String password);
	
	Member findByMemberId(String member_id);

	Member findByNameAndPhone(String name, String phone);

	Member findByMemberIdAndNameAndPhone(String member_id, String name, String phone);

	long countByMemberId(String memberId);	    // ID 중복 검사
	  
	List<Member> findAllByMemberId(String memberId);	// 회원정보 불러오기
	
	Member findAllByMemberIdAndAcceptLocation(String memberId, boolean acceptLocation);	//	회원주소검색
	
//	// admin 회원 목록 조회
//	@Query(value="select m.MEMBER_ID, NAME, PHONE, GRADE, MILE, count(c.COUPON_NUM) as cnt from member m left join coupon c on m.MEMBER_ID = c.MEMBER_ID group by m.MEMBER_ID", nativeQuery=true)
//	public List<AdminUserListResponseDTO> listAdminMember();
	
	// admin 회원 목록 조회
	@Query(value="select m.MEMBER_ID, NAME, PHONE, GRADE, MILE, count(c.COUPON_NUM) as cnt from member m left join coupon c on m.MEMBER_ID = c.MEMBER_ID group by m.MEMBER_ID", nativeQuery=true)
	public Slice<AdminUserListResponseDTO> listAdminMember(Pageable pageable);
	
	// admin 회원 검색
	@Query(value="select m.MEMBER_ID, NAME, PHONE, GRADE, MILE, count(c.COUPON_NUM) as cnt from member m left join coupon c on m.MEMBER_ID = c.MEMBER_ID where m.MEMBER_ID like %:memberId% or NAME like %:memberId% group by m.MEMBER_ID", nativeQuery=true)
	public List<AdminUserListResponseDTO> findByMemberIdOrName(@Param("memberId") String memberId);
	
	
	
	@Query(value="select count(DISTINCT c.board_num ) as cnt from member m left join bid c on m.MEMBER_ID = c.MEMBER_ID where m.member_id=:memberId ", nativeQuery=true)
	public int bidCount(@Param("memberId") String memberId);
	
	@Query(value="select count(*) as cnt from member m left join userlike c on m.MEMBER_ID = c.MEMBER_ID where m.member_id=:memberId " , nativeQuery=true)
	public int uselike(@Param("memberId") String memberId);
	
	@Query(value="select count(*) as cnt from member m left join payment c on m.MEMBER_ID = c.MEMBER_ID where m.member_id=:memberId " , nativeQuery=true)
	public int payment(@Param("memberId") String memberId);
	
	
	@Query(value="select sum(c.prod_price) from member m left join payment c on m.MEMBER_ID = c.MEMBER_ID where m.member_id=:memberId  " , nativeQuery=true)
	public int payamount(@Param("memberId") String memberId);
	
	@Query(value="select sum(c.point) as sum from member m left join mile c on m.MEMBER_ID = c.MEMBER_ID where m.member_id=:memberId " , nativeQuery=true)
	public int mileAmount(@Param("memberId") String memberId);
}
