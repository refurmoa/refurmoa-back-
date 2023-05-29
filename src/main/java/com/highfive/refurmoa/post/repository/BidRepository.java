package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Bid;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BidRepository extends JpaRepository<Bid, Integer> {

    Page<Bid> findByBoardBoardNumAndBidCancelFalseOrderByBidDateDesc(int boardNum, Pageable pageable); // 입찰 목록 조회

}