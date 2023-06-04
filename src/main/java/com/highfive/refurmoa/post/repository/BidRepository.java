package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Bid;
import com.highfive.refurmoa.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BidRepository extends JpaRepository<Bid, Integer> {

    Page<Bid> findByBoardBoardNumAndBidCancelFalseOrderByBidDateDesc(int boardNum, Pageable pageable); // 입찰 목록 조회
  
    Long countByBoardBoardNumAndBidCancelFalse(int board_num); // 게시글 입찰 수 조회

}