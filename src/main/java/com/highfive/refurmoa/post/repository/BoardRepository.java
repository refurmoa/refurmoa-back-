package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;

import org.springframework.data.jpa.repository.JpaRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findByBoardNum(int boardNum);

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.deleteCheck = true WHERE b.boardNum = :boardNum")
    void updateDeleteCheckByBoardNum(@Param("boardNum") int boardNum); // 판매 글 삭제

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board b SET b.curPrice = :curPrice WHERE b.boardNum = :boardNum")
    void updateCurPriceByBoardNum(int curPrice, int boardNum); // 현재가 변경
}
