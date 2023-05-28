package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Userlike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserlikeRepository extends JpaRepository<Userlike, Integer> {
    Userlike findByBoardNumAndMemberId(int boardNum, String memberId);
}
