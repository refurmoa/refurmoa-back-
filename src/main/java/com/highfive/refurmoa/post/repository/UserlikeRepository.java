package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Userlike;

import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserlikeRepository extends JpaRepository<Userlike, Integer> {

    Userlike findByBoardBoardNumAndMemberMemberId(int board_num, String member_id);
  
    List<Userlike> findByMemberMemberId(String memberId);
  //찜목록
  	@Query(value="select count(*) as cnt from  userlike where member_id=:memberId " , nativeQuery=true)
  	public Integer uselike(@Param("memberId") String memberId);
}
