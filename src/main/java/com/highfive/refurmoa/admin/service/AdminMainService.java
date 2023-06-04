package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.response.AdminCountResponseDTO;
import com.highfive.refurmoa.admin.dto.response.DailySalesResponseDTO;

public interface AdminMainService {
    AdminCountResponseDTO getAdminCount();
    DailySalesResponseDTO getDailySales();
}
