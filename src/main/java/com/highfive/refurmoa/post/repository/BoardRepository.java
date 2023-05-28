package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    Board findByBoardNum(int boardNum);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board SET delete_check = 1 WHERE board_num = :boardNum", nativeQuery = true)
    void updateDeleteCheckByBoardNum(int boardNum); // 판매 글 삭제

}
