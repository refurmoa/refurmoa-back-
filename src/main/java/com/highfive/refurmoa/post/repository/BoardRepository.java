package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Integer> {
    Board findByBoardNum(int boardNum);
}
