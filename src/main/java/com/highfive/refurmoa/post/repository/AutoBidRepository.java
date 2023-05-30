package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.AutoBid;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AutoBidRepository extends JpaRepository<AutoBid, Integer> {

    AutoBid findFirstByBoardBoardNumAndAutobidPriceGreaterThanOrderByAutobidDateAsc(int boardNum, int price); // 자동입찰 조회

}