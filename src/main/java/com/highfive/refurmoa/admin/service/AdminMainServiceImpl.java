package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.response.AdminCountResponseDTO;
import com.highfive.refurmoa.admin.dto.response.DailySalesResponseDTO;
import com.highfive.refurmoa.admin.dto.response.DaySalesDTO;
import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.cs.repository.InquiryRepository;
import com.highfive.refurmoa.post.repository.*;
import com.highfive.refurmoa.prod.repository.ProductRepository;
import com.highfive.refurmoa.user.repository.DeliveryRepository;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class AdminMainServiceImpl implements AdminMainService {
    private final BoardRepository boardRepository;
    private final BidRepository bidRepository;
    private final DeliveryRepository deliveryRepository;
    private final PaymentRepository paymentRepository;
    private final ProdInqRepository prodInqRepository;
    private final ProdInqReplyRepository prodInqReplyRepository;
    private final InquiryRepository inquiryRepository;
    private final ProdPartnerRepository prodPartnerRepository;

    public AdminMainServiceImpl(BoardRepository boardRepository, BidRepository bidRepository, DeliveryRepository deliveryRepository,
                                PaymentRepository paymentRepository, ProdInqRepository prodInqRepository, ProdInqReplyRepository prodInqReplyRepository,
                                InquiryRepository inquiryRepository, ProdPartnerRepository prodPartnerRepository) {
        this.boardRepository = boardRepository;
        this.bidRepository = bidRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentRepository = paymentRepository;
        this.prodInqRepository = prodInqRepository;
        this.prodInqReplyRepository = prodInqReplyRepository;
        this.inquiryRepository = inquiryRepository;
        this.prodPartnerRepository = prodPartnerRepository;
    }

    // 어드민 메인페이지 navBar 개수
    @Override
    public AdminCountResponseDTO getAdminCount() {
        Long yet = boardRepository.getAdminCountYet();
        Long ingauction = boardRepository.getAdminCountIngauction();
        Long ingdirect = boardRepository.getAdminCountIngdirect();
        Long waitpay = bidRepository.getAdminCountWaitpay();
        Long prepare = deliveryRepository.getPrepareCount();
        Long shipping = deliveryRepository.getAdminCountShipping();
        Long completed = deliveryRepository.getAdminCountCompleted();
        Long productInquiry = prodInqRepository.count() - prodInqReplyRepository.count();
        Long oneononeInquiry = inquiryRepository.getAdminCountOneononeInquiry();
        Long partnership = prodPartnerRepository.getAdminCountPartnership();
        return new AdminCountResponseDTO(yet, ingauction, ingdirect, waitpay, prepare, shipping,
                completed, productInquiry, oneononeInquiry, partnership);
    }

    // DAILY SALES 일별 매출
    @Override
    public DailySalesResponseDTO getDailySales() {
        List<DaySalesDTO> daySalesList = new ArrayList<>();

        for (int j = 6; j >= 0; j--) {
            LocalDate beforeday = LocalDate.now().minusDays(j);
            LocalDateTime startDateTime = LocalDateTime.of(beforeday, LocalTime.MIN); // 시작일 00:00:00
            LocalDateTime endDateTime = LocalDateTime.of(beforeday, LocalTime.MAX); // 종료일 23:59:59
            Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
            daySalesList.add(new DaySalesDTO(beforeday,
                    paymentRepository.sumDailyAuction(start, end),
                    paymentRepository.sumDailyDirect(start, end),
                    paymentRepository.sumCancel(start, end),
                    paymentRepository.sumCount(start, end)));
        }

        return new DailySalesResponseDTO(daySalesList);

    }
}
