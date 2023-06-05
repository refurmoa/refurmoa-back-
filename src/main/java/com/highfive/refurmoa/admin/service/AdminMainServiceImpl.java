package com.highfive.refurmoa.admin.service;

import com.highfive.refurmoa.admin.dto.request.WriteMemoDTO;
import com.highfive.refurmoa.admin.dto.response.*;
import com.highfive.refurmoa.admin.repository.MemoRepository;
import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.cs.repository.InquiryRepository;
import com.highfive.refurmoa.entity.Memo;
import com.highfive.refurmoa.entity.ProdInquiry;
import com.highfive.refurmoa.entity.ProdInquiryReply;
import com.highfive.refurmoa.pay.repository.DeliveryRepository;
import com.highfive.refurmoa.pay.repository.PaymentRepository;
import com.highfive.refurmoa.post.repository.*;
import org.springframework.stereotype.Service;

import java.time.*;
import java.util.ArrayList;
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
    private final MemoRepository memoRepository;

    public AdminMainServiceImpl(BoardRepository boardRepository, BidRepository bidRepository, DeliveryRepository deliveryRepository,
                                PaymentRepository paymentRepository, ProdInqRepository prodInqRepository, ProdInqReplyRepository prodInqReplyRepository,
                                InquiryRepository inquiryRepository, ProdPartnerRepository prodPartnerRepository, MemoRepository memoRepository) {
        this.boardRepository = boardRepository;
        this.bidRepository = bidRepository;
        this.deliveryRepository = deliveryRepository;
        this.paymentRepository = paymentRepository;
        this.prodInqRepository = prodInqRepository;
        this.prodInqReplyRepository = prodInqReplyRepository;
        this.inquiryRepository = inquiryRepository;
        this.prodPartnerRepository = prodPartnerRepository;
        this.memoRepository= memoRepository;
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
            LocalDate day = LocalDate.now().minusDays(j);
            LocalDateTime startDateTime = LocalDateTime.of(day, LocalTime.MIN); // 시작일 00:00:00
            LocalDateTime endDateTime = LocalDateTime.of(day, LocalTime.MAX); // 종료일 23:59:59
            Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
            daySalesList.add(new DaySalesDTO(day,
                    paymentRepository.sumDailyAuction(start, end),
                    paymentRepository.sumDailyDirect(start, end),
                    paymentRepository.sumCancel(start, end),
                    paymentRepository.sumCount(start, end)));
        }

        return new DailySalesResponseDTO(daySalesList);

    }

    // 카테고리별 매출
    @Override
    public CategorySalesResponseDTO getCategorySales() {
        List<CategoryCountDTO> categoryCount = new ArrayList<>();
        categoryCount.add(new CategoryCountDTO("appkitchen", paymentRepository.sumCategorySalesCount("appkitchen")));
        categoryCount.add(new CategoryCountDTO("applife", paymentRepository.sumCategorySalesCount("applife")));
        categoryCount.add(new CategoryCountDTO("appelec", paymentRepository.sumCategorySalesCount("appelec")));
        categoryCount.add(new CategoryCountDTO("furliving", paymentRepository.sumCategorySalesCount("furliving")));
        categoryCount.add(new CategoryCountDTO("furbed", paymentRepository.sumCategorySalesCount("furbed")));
        categoryCount.add(new CategoryCountDTO("furoffice", paymentRepository.sumCategorySalesCount("furoffice")));
        return new CategorySalesResponseDTO(categoryCount);
    }

    // 월별 매출 조회
    @Override
    public MonthlySalesResponseDTO getMonthlySales() {
        List<MonthSalesDTO> monthSales = new ArrayList<>();

        for (int j = 11; j >= 0; j--) {
            YearMonth yearMonth = YearMonth.now().minusMonths(j); // 현재 년도와 월을 가져옴
            LocalDate firstDayOfMonth = yearMonth.atDay(1); // 해당 월의 첫날
            LocalDate lastDayOfMonth = yearMonth.atEndOfMonth(); // 해당 월의 마지막날

            LocalDateTime startDateTime = LocalDateTime.of(firstDayOfMonth, LocalTime.MIN); // 해당 월의 첫날 00:00:00
            LocalDateTime endDateTime = LocalDateTime.of(lastDayOfMonth, LocalTime.MAX); // 해당 월의 마지막날 23:59:59

            Date start = Date.from(startDateTime.atZone(ZoneId.systemDefault()).toInstant());
            Date end = Date.from(endDateTime.atZone(ZoneId.systemDefault()).toInstant());
            monthSales.add(new MonthSalesDTO(yearMonth, paymentRepository.sumMonthlySales(start, end)));
        }
        return new MonthlySalesResponseDTO(monthSales);
    }

    // 미답변 상품 문의 목록 조회
    public List<UnansweredResponseDTO> getUnanswered() {
        List<ProdInquiry> prodInquiries = prodInqRepository.findAll();
        List<ProdInquiryReply> prodInquiryReplies = prodInqReplyRepository.findAll();

        List<ProdInquiry> inquiriesWithoutReply = new ArrayList<>();
        for (ProdInquiry prodInquiry : prodInquiries) {
            boolean hasReply = false;
            for (ProdInquiryReply prodInquiryReply : prodInquiryReplies) {
                if (prodInquiry.getBoard().getBoardNum() == prodInquiryReply.getBoard().getBoardNum()) {
                    hasReply = true;
                    break;
                }
            }
            if (!hasReply) {
                inquiriesWithoutReply.add(prodInquiry);
            }
        }

        List<UnansweredResponseDTO> unansweredResponseDTO = new ArrayList<>();
        for (ProdInquiry prodInquiry : inquiriesWithoutReply) {
            UnansweredResponseDTO dto = new UnansweredResponseDTO(prodInquiry);
            unansweredResponseDTO.add(dto);
        }

        return unansweredResponseDTO;
    }

    // 메모 목록 조회
    public List<MemoResponseDTO> getMemoList() {
        List<Memo> memos = memoRepository.findAllByOrderByMemoNumDesc();
        List<MemoResponseDTO> memoResponseDTOList = new ArrayList<>();
        for (Memo memo : memos) {
            MemoResponseDTO dto = new MemoResponseDTO(memo);
            memoResponseDTOList.add(dto);
        }
        return memoResponseDTOList;
    }

    // 메모 등록
    public void writeMemo(WriteMemoDTO writeMemoDTO) {
        Memo memo = new Memo(writeMemoDTO.getContent());
        memoRepository.save(memo);
    }
}
