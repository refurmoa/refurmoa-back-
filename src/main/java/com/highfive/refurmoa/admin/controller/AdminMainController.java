package com.highfive.refurmoa.admin.controller;


import com.highfive.refurmoa.admin.dto.response.AdminCountResponseDTO;
import com.highfive.refurmoa.admin.dto.response.DailySalesResponseDTO;
import com.highfive.refurmoa.admin.service.AdminMainServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminMainController {

    private final AdminMainServiceImpl adminMainServiceImpl;

    @GetMapping("/admin/count")
    public AdminCountResponseDTO getAdminCount() {
        return adminMainServiceImpl.getAdminCount();
    }

    @GetMapping("/admin/sales/daily")
    public DailySalesResponseDTO getDailySales() {
        return adminMainServiceImpl.getDailySales();
    }
}
