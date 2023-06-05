package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteMemoDTO;
import com.highfive.refurmoa.admin.dto.response.*;

import java.util.List;

public interface AdminMainService {
    AdminCountResponseDTO getAdminCount(); // 어드민 메인페이지 navBar 개수
    DailySalesResponseDTO getDailySales(); // DAILY SALES 일별 매출
    CategorySalesResponseDTO getCategorySales(); // 카테고리별 매출
    MonthlySalesResponseDTO getMonthlySales(); // 월별 매출 조회
    List<UnansweredResponseDTO> getUnanswered(); // 미답변 상품 문의 목록 조회
    List<MemoResponseDTO> getMemoList(); // 메모 목록 조회
    void writeMemo(WriteMemoDTO writeMemoDTO); // 메모 등록
}
