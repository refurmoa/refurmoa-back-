package com.highfive.refurmoa.pay.repository;

import java.util.Date;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.highfive.refurmoa.entity.Payment;

import jakarta.transaction.Transactional;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {

    // 결제 내역 조회
    Page<Payment> findByMemberMemberId(String memberId, Pageable pageable);

    // 결제 내역 기간 조회
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND :startDate < p.payDate " +
            "AND p.payDate < :endDate"
    )
    Page<Payment> findByMemberIdAndPeriod(@Param("memberId") String memberId,@Param("startDate") Date startDate,@Param("endDate") Date endDate, Pageable pageable);

    // 결제 내역 검색
    @Query("SELECT p FROM Payment p " +
            "WHERE p.member.memberId = :memberId " +
            "AND p.product.prodName like %:search%"
    )
    Page<Payment> findByMemberIdAndSearch(@Param("memberId") String memberId,@Param("search") String search, Pageable pageable);

    // 상품 코드로 결제 내역 조회
    Payment findByProductProductCode(int productCode);

    // 하루 경매 매출액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p LEFT JOIN board b " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND b.sellType != 2 " +
            "AND p.prodPrice != b.directPrice"
    )
    Long sumDailyAuction(Date startDate, Date endDate);

    // 하루 즉시구매 매출액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p LEFT JOIN board b " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND b.sellType != 1 " +
            "AND p.prodPrice = b.directPrice"
    )
    Long sumDailyDirect(Date startDate, Date endDate);

    // 하루 취소금액
    @Query("SELECT SUM(p.payPrice) / 1000 FROM Payment p " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND p.payCancel"
    )
    Long sumCancel(Date startDate, Date endDate);

    // 하루 판매건수
    @Query("SELECT COUNT(p) FROM Payment p " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND NOT p.payCancel"
    )
    Long sumCount(Date startDate, Date endDate);

    // 카테고리별 판매건수
    @Query("SELECT COUNT(p) FROM Payment p LEFT JOIN product product " +
            "WHERE product.category LIKE :category"
    )
    Long sumCategorySalesCount(String category);

    // 월별 매출 조회
    @Query("SELECT SUM(p.payPrice) / 10000 FROM Payment p " +
            "WHERE p.payDate BETWEEN :startDate AND :endDate " +
            "AND NOT p.payCancel"
    )
    Long sumMonthlySales(Date startDate, Date endDate);
    
    @Query(value="select count(*) as cnt from payment where member_id=:memberId and pay_cancel=0" , nativeQuery=true)
	public int payment(@Param("memberId") String memberId);

    // board_num으로 결제정보 찾기
    Payment findByBoardBoardNum(int boardNum);
    
    @Modifying 
    @Transactional
	@Query(value="update payment as m, product as d set m.pay_cancel = 1, d.prod_state = 2 where m.product_code = :productCode and m.pay_num = :id and d.product_code = :productCode", nativeQuery=true)
	void canclePay(@Param("id") String id, @Param("productCode") int productCode);
    
    @Query(value="select sum(prod_price) from payment where member_id=:memberId" , nativeQuery=true)
	public Integer payamount(@Param("memberId") String memberId);
}
