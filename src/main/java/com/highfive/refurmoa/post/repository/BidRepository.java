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

    // 어드민 메인페이지
    // 입금대기(경매)
    @Query("SELECT COUNT(b) FROM Bid b " +
            "WHERE b.board.sellType != 2 " +
            "AND NOT b.board.deleteCheck " +
            "AND NOT b.board.startDate < CURRENT_TIMESTAMP " +
            "AND b.board.curPrice = b.bidPrice " +
            "AND NOT b.bidCancel"
    )
    Long getAdminCountWaitpay();
}