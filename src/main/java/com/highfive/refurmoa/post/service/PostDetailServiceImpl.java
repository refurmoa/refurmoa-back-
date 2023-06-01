package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.*;
import com.highfive.refurmoa.post.dto.reponse.*;
import com.highfive.refurmoa.post.dto.request.*;
import com.highfive.refurmoa.post.repository.*;

import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Service
public class PostDetailServiceImpl implements PostDetailService {

    private final BoardRepository boardRepository;
    private final UserlikeRepository userlikeRepository;
    private final BidRepository bidRepository;
    private final AutoBidRepository autoBidRepository;
    private final ProdInqRepository prodInqRepository;
    private final ProdInqReplyRepository prodInqReplyRepository;

    public PostDetailServiceImpl(BoardRepository boardRepository, UserlikeRepository userlikeRepository,
                                 BidRepository bidRepository, AutoBidRepository autoBidRepository,
                                 ProdInqRepository prodInqRepository, ProdInqReplyRepository prodInqReplyRepository) {
        this.boardRepository = boardRepository;
        this.userlikeRepository = userlikeRepository;
        this.bidRepository = bidRepository;
        this.autoBidRepository = autoBidRepository;
        this.prodInqRepository = prodInqRepository;
        this.prodInqReplyRepository = prodInqReplyRepository;
    }


    @Override // 판매 상세 정보 조회
    public PostDetailResponseDTO getPostDetail(int boardNum, String memberId) {
        Board board = boardRepository.findByBoardNum(boardNum);
        if (memberId != null) {
            Userlike userlike = userlikeRepository.findByBoardBoardNumAndMemberMemberId(board.getBoardNum(), memberId);
            return new PostDetailResponseDTO(board, userlike);
        } else {
            return new PostDetailResponseDTO(board, null);
        }
    }

    @Override // 판매 글 삭제
    public void deletePost(int boardNum) {
        boardRepository.updateDeleteCheckByBoardNum(boardNum);
    }

    @Override // 입찰(+자동입찰)
    public void insertBid(BidRequestDTO bidRequestDTO) {
        bidRepository.save(new Bid(bidRequestDTO));
        boardRepository.updateCurPriceByBoardNum(bidRequestDTO.getBidPrice(), bidRequestDTO.getBoardNum());
        AutoBid autoBid = autoBidRepository
                .findFirstByBoardBoardNumAndAutobidPriceGreaterThanOrderByAutobidDateAsc
                        (bidRequestDTO.getBoardNum(), bidRequestDTO.getBidPrice());
        if (autoBid != null) {
            bidRepository.save(new Bid(autoBid, bidRequestDTO.getBidPrice() + bidRequestDTO.getUnitPrice()));
        }
    }

    @Override // 자동입찰
    public void insertAutoBid(BidRequestDTO bidRequestDTO) {
        autoBidRepository.save(new AutoBid(bidRequestDTO));
        insertBid(bidRequestDTO);
    }

    @Override // 입찰 목록 조회
    public Page<BidListResponseDTO> getListBid(int boardNum, Pageable pageable) {
        Page<Bid> bidList = bidRepository.findByBoardBoardNumAndBidCancelFalseOrderByBidDateDesc(boardNum, pageable);
        return bidList.map(BidListResponseDTO::new);
    }

    @Override // 상품 문의 글 목록 조회
    public Page<ProdInqListResponseDTO> getListInq(int boardNum, Pageable pageable) {
        Page<ProdInquiry> prodInqList = prodInqRepository.findByBoardBoardNumOrderByProdInquiryNumDesc(boardNum, pageable);
        return prodInqList.map(prodInq -> {
            ProdInquiryReply re = prodInqReplyRepository.findByProdInquiryProdInquiryNum(prodInq.getProdInquiryNum());
            ProdInqListResponseDTO responseDTO = new ProdInqListResponseDTO(prodInq, re);
            return responseDTO;
        });
    }

    @Override // 상품 문의 글 등록
    public void insertProdInquiry(ProdInqRequestDTO prodInquiryDTO) {
        prodInquiryDTO.setProductCode(boardRepository.findByBoardNum(prodInquiryDTO.getBoardNum()).getProduct().getProductCode());
        ProdInquiry prodInquiry = new ProdInquiry(prodInquiryDTO);
        prodInqRepository.save(prodInquiry);
    }

    @Override // 상품 문의 글 삭제
    public void deleteProdInquiry(int prodInquiryNum) {
        prodInqRepository.deleteById(prodInquiryNum);
    }

    @Override // 상품 문의 댓글 등록
    public void insertProdInqReply(ProdInqReplyRequestDTO prodInqReplyRequestDTO) {
        ProdInquiry prodInquiry = prodInqRepository.findByProdInquiryNum(prodInqReplyRequestDTO.getProdInquiryNum());
        ProdInquiryReply prodInquiryReply = new ProdInquiryReply(prodInqReplyRequestDTO, prodInquiry);
        prodInqReplyRepository.save(prodInquiryReply);
    }

}
