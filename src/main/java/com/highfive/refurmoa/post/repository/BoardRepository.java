package com.highfive.refurmoa.post.repository;

import com.highfive.refurmoa.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Date;

public interface BoardRepository extends JpaRepository<Board, Integer> {

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusAllAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusAllAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                              @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.startDate > :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusIngNEndAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                              @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngNEndAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                                  @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.startDate < :now OR NOT b.endDate > :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusYetNEndAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                              @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :now OR NOT b.endDate > :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNEndAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                                  @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.endDate < :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusYetNIngAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                              @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate < :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetNIngAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                                  @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.startDate < :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusYetAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                                  @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate < :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusYetAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.startDate > :now AND NOT b.endDate < :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusIngAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.startDate > :now AND NOT b.endDate < :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusIngAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

//    @Query("SELECT b FROM Board b LEFT JOIN product p " +
//            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
//            "AND NOT b.endDate > :now " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, (CASE WHEN b.endDate > :now THEN 0 ELSE 1 END) ASC")
//    Page<Board> findSellStatusEndAndClose(@Param("sellType") int sellType, @Param("search")String search,
//                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

    @Query("SELECT b FROM Board b LEFT JOIN product p " +
            "WHERE b.deleteCheck = false AND b.sellType != :sellType " +
            "AND NOT b.endDate > :now " +
            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END")
    Page<Board> findSellStatusEndAndNewOrView(@Param("sellType") int sellType, @Param("search")String search,
                                          @Param("category") String category, @Param("now") Date now, Pageable pageable);

    Board findByBoardNum(int boardNum);
}






























//    // 삭제여부x, 판매방식 전체, 진행상태 전체, 카테고리(like), 경매중 or 끝나는시간 없는 글 상위로 나머지 하위로 그다음 게시글 조회수순 정렬
//    @Query("SELECT b FROM Board b LEFT JOIN Product p " +
//            "WHERE b.deleteCheck = false AND b.sellType >= :startSellType AND b.sellType <= :endSellType " +
//            "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//            "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, b.readCount DESC")
//    Page<Board> findSellStatusAllAndView(@Param("startSellType") int startSellType, @Param("endSellType") int endSellType,
//                                         @Param("search")String search, @Param("category") String category, @Param("now") Date now, Pageable pageable);
// 삭제여부x, 판매방식 전체, 진행상태(IngNEnd), 카테고리(like), 경매중 or 끝나는시간 없는 글 상위로 나머지 하위로 그다음 게시글 조회순 정렬
//@Query("SELECT b FROM Board b LEFT JOIN Product p " +
//        "WHERE b.deleteCheck = false AND b.sellType >= :startSellType AND b.sellType <= :endSellType " +
//        "AND (b.startDate < :now AND b.endDate > :now) AND (b.endDate < :now) " +
//        "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//        "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, b.readCount DESC")
//Page<Board> findSellStatusIngNEndAndView(@Param("startSellType") int startSellType, @Param("endSellType") int endSellType,
//                                         @Param("search")String search, @Param("category") String category, @Param("now") Date now, Pageable pageable);
// 삭제여부x, 판매방식 전체, 진행상태(YetNEnd), 카테고리(like), 경매중 or 끝나는시간 없는 글 상위로 나머지 하위로 그다음 게시글 조회순 정렬
//@Query("SELECT b FROM Board b LEFT JOIN Product p " +
//        "WHERE b.deleteCheck = false AND b.sellType >= :startSellType AND b.sellType <= :endSellType " +
//        "AND (b.startDate > :now) AND (b.endDate < :now) " +
//        "AND p.prodName LIKE %:search% AND p.category LIKE %:category% " +
//        "ORDER BY CASE WHEN (b.endDate > :now) OR (b.endDate is null) THEN 0 ELSE 1 END, b.readCount DESC")
//Page<Board> findSellStatusYetNEndAndView(@Param("startSellType") int startSellType, @Param("endSellType") int endSellType,
//                                         @Param("search")String search, @Param("category") String category, @Param("now") Date now, Pageable pageable);