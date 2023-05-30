package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Userlike;
import org.apache.catalina.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserlikeRepository extends JpaRepository<Userlike, Integer> {
    Userlike findByBoardBoardNumAndMemberMemberId(int board_num, String member_id);
    List<Userlike> findByMemberMemberId(String memberId);
}
