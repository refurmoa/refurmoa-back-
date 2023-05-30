package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findByBoardNum(int boardNum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board SET delete_check = 1 WHERE board_num = :boardNum", nativeQuery = true)
    void updateDeleteCheckByBoardNum(int boardNum); // 판매 글 삭제

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board SET cur_price = :curPrice WHERE board_num = :boardNum", nativeQuery = true)
    void updateCurPriceByBoardNum(int curPrice, int boardNum); // 현재가 변경
}
