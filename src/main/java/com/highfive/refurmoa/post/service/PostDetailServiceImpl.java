package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.*;
import com.highfive.refurmoa.post.dto.BidListResponseDTO;
import com.highfive.refurmoa.post.dto.PostDetailResponseDTO;
import com.highfive.refurmoa.post.dto.ProdInqListResponseDTO;
import com.highfive.refurmoa.post.dto.ProdInqRequestDTO;
import com.highfive.refurmoa.post.repository.BidRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.ProdInqRepository;
import com.highfive.refurmoa.post.repository.UserlikeRepository;
import org.apache.catalina.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PostDetailServiceImpl implements PostDetailService {

    private final BoardRepository boardRepository;
    private final UserlikeRepository userlikeRepository;
    private final BidRepository bidRepository;
    private final ProdInqRepository prodInqRepository;

    public PostDetailServiceImpl(BoardRepository boardRepository, UserlikeRepository userlikeRepository, BidRepository bidRepository, ProdInqRepository prodInqRepository) {
        this.boardRepository = boardRepository;
        this.userlikeRepository = userlikeRepository;
        this.bidRepository = bidRepository;
        this.prodInqRepository = prodInqRepository;
    }


    @Override // 판매 상세 정보 조회
    public PostDetailResponseDTO getPostDetail(int boardNum, String memberId) {
        Board board = boardRepository.findByBoardNum(boardNum);
        if (memberId != null) {
            Userlike userlike = userlikeRepository.findByBoardNumAndMemberId(board.getBoardNum(), memberId);
            return new PostDetailResponseDTO(board, userlike);
        } else {
            return new PostDetailResponseDTO(board, null);
        }
    }

    @Override // 입찰 목록 조회
    public Page<BidListResponseDTO> getListBid(int boardNum, Pageable pageable) {
        Page<Bid> bidList = bidRepository.findByBoardBoardNumAndBidCancelFalseOrderByBidDateDesc(boardNum, pageable);
        return bidList.map(BidListResponseDTO::new);
    }

    @Override // 상품 문의 글 목록 조회
    public Page<ProdInqListResponseDTO> getListInq(int boardNum, Pageable pageable) {
        Page<ProdInquiry> prodInqList= prodInqRepository.findByBoardBoardNumOrderByProdInquiryNumDesc(boardNum, pageable);
        return prodInqList.map(ProdInqListResponseDTO::new);
    }

    @Override // 상품 문의 글 등록
    public int insertProdInquiry(ProdInqRequestDTO prodInquiryDTO) {
        prodInquiryDTO.setProductCode(boardRepository.findByBoardNum(prodInquiryDTO.getBoardNum()).getProduct().getProductCode());
        ProdInquiry prodInquiry = new ProdInquiry(prodInquiryDTO);
        try { prodInqRepository.save(prodInquiry); return 1; }
        catch (Exception e) { return 0; }
    }




}
