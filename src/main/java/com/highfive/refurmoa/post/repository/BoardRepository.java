package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Date;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Integer> {

    // 판매상태(all)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.startDate = :date_now " + // 오류방지
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusAllAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                              @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Ing and End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngNEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet and End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :date_now OR NOT b.endDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet and Ing)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNIngAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                                  @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Yet)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(Ing)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :date_now AND NOT b.endDate < :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    // 판매상태(End)
    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate > :date_now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :date_now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusEndAndNewOrView(@Param("sellType") int sellType, @Param("search") String search,
                                          @Param("category") String category, @Param("date_now") Date date_now, Pageable pageable);

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.readCount = b.readCount + 1 WHERE b.boardNum = :boardNum")
    void findByBoardNumAndUpdatePlusReadCount(@Param("boardNum") int boardNum);

    Board findByBoardNum(int boardNum);

    @Transactional
    @Modifying
    @Query("UPDATE Board b SET b.deleteCheck = true WHERE b.boardNum = :boardNum")
    void updateDeleteCheckByBoardNum(@Param("boardNum") int boardNum); // 판매 글 삭제

    @Transactional
    @Modifying
    @Query(value = "UPDATE Board b SET b.curPrice = :curPrice WHERE b.boardNum = :boardNum")
    void updateCurPriceByBoardNum(int curPrice, int boardNum); // 현재가 변경

    List<Board> findByProductProductCodeAndDeleteCheckFalseOrderByBoardNumDesc(@Param("productCode") int productCode);
    
    
    @Query("select b from Board b where b.endDate>:date order by b.readCount DESC LIMIT 15")
    List<Board> mainBest(@Param("date") Date date);
    
    @Query("select b from Board b  where b.endDate>:date order by b.boardNum DESC LIMIT 15")
    List<Board> mainStart(@Param("date") Date date);
    
    @Query("select b from Board b where NOT b.sellType =2 and b.endDate>:date and b.startDate<:date order by b.endDate ASC LIMIT 15")
    List<Board> mainEnd(@Param("date") Date date);

    Board findByBoardNumAndDeleteCheckFalse(int boardNum); // 결제(상품) 정보 조회
    
    // 어드민 메인페이지
    // 판매예정
    @Query("SELECT COUNT(b) FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false " +
            "AND b.startDate > CURRENT_TIMESTAMP " +
            "OR p.prodState = 0"
    )
    Long getAdminCountYet();
    // 판매중(경매)
    @Query("SELECT COUNT(b) FROM Board b " +
            "WHERE b.deleteCheck = false AND b.sellType != 2 " +
            "AND NOT b.startDate > CURRENT_TIMESTAMP AND NOT b.endDate < CURRENT_TIMESTAMP"
    )
    Long getAdminCountIngauction();
    // 판매중(즉시구매)
    @Query("SELECT COUNT(b) FROM Board b " +
            "WHERE b.deleteCheck = false AND b.sellType != 1 " +
            "AND NOT b.startDate > CURRENT_TIMESTAMP AND NOT b.endDate < CURRENT_TIMESTAMP"
    )
    Long getAdminCountIngdirect();
}
