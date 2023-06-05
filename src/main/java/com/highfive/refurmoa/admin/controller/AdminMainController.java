package com.highfive.refurmoa.admin.controller;


import com.highfive.refurmoa.admin.dto.request.WriteMemoDTO;
import com.highfive.refurmoa.admin.dto.response.*;
import com.highfive.refurmoa.admin.service.AdminMainServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class AdminMainController {

    private final AdminMainServiceImpl adminMainServiceImpl;

    // 어드민 메인페이지 navBar 개수
    @GetMapping("/admin/count")
    public AdminCountResponseDTO getAdminCount() {
        return adminMainServiceImpl.getAdminCount();
    }

    // DAILY SALES 일별 매출
    @GetMapping("/admin/sales/daily")
    public DailySalesResponseDTO getDailySales() {
        return adminMainServiceImpl.getDailySales();
    }

    // 카테고리별 매출
    @GetMapping("/admin/sales/category")
    public CategorySalesResponseDTO getCategorySales() {
        return adminMainServiceImpl.getCategorySales();
    }

    // 월별 매출 조회
    @GetMapping("/admin/sales/monthly")
    public MonthlySalesResponseDTO getMonthlySales() {
        return adminMainServiceImpl.getMonthlySales();
    }

    // 미답변 상품 문의 목록 조회
    @GetMapping("/admin/inq/unanswered")
    public List<UnansweredResponseDTO> getUnanswered() {
        return adminMainServiceImpl.getUnanswered();
    }

    // 메모 목록 조회
    @GetMapping("/admin/memo")
    public List<MemoResponseDTO> getMemoList() {
        return adminMainServiceImpl.getMemoList();
    }

    // 메모 등록
    @PostMapping("/admin/memo/write")
    public void writeMemo(@RequestBody WriteMemoDTO writeMemoDTO) {
        adminMainServiceImpl.writeMemo(writeMemoDTO);
    }
}
