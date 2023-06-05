package com.highfive.refurmoa.cs.repository;

import com.highfive.refurmoa.entity.FaqBoard;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FaqBoardRepository extends JpaRepository<FaqBoard, Integer> {

    // Faq 목록 조회
    Page<FaqBoard> findAll(Pageable pageable);
    // Faq 카테고리별 목록 조회
    Page<FaqBoard> findByFaqCate(int faq_cate,Pageable pageable);

    // Faq 목록 검색(제목)
    Page<FaqBoard> findByFaqTitleContaining(String search, Pageable pageable);

    // Faq 삭제
    @Transactional
    void deleteByFaqNum(int faq_num);

    // Faq 수정을 위한 faq_num에 해당하는 글 검색
    FaqBoard findByFaqNum(int faq_num);
    //조회수
    @Transactional
    @Modifying
    @Query("UPDATE FaqBoard b SET b.readCount = b.readCount + 1 WHERE b.faqNum = :faqNum")
    void findByFaqNumAndUpdatePlusReadCount(@Param("faqNum") int faqNum);
}
