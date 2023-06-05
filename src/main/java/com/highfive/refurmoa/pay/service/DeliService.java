package com.highfive.refurmoa.pay.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RequestParam;

import com.highfive.refurmoa.pay.dto.response.DeliResponseDTO;

public interface DeliService {
	public Page<DeliResponseDTO> adminOrder(String search,Pageable pagealbe);
}
