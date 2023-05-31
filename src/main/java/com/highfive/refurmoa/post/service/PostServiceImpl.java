package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Userlike;
import com.highfive.refurmoa.post.Post;
import com.highfive.refurmoa.post.dto.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.PostRequestDTO;
import com.highfive.refurmoa.post.dto.PostResponseDTO;
import com.highfive.refurmoa.post.repository.BidRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.UserlikeRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    private final BoardRepository boardRepository;
    private final BidRepository bidRepository;
    private final UserlikeRepository userlikeRepository;

    public PostServiceImpl(BoardRepository boardRepository, BidRepository bidRepository, UserlikeRepository userlikeRepository) {
        this.boardRepository = boardRepository;
        this.bidRepository = bidRepository;
        this.userlikeRepository = userlikeRepository;
    }

    // 판매글 목록 가져오기
    @Override
    public Page<PostResponseDTO> getPostList(PostRequestDTO postRequestDTO) {
        Date date_now = new Date();// 현재시간

        Pageable pageable = PageRequest.of(postRequestDTO.getPage(), postRequestDTO.getSize());
        Sort sort = Sort.by(Sort.Direction.DESC, "boardNum");

        if (postRequestDTO.getOrderby().equals("new")) {
            sort = Sort.by(Sort.Direction.DESC, "boardNum");
            pageable = PageRequest.of(postRequestDTO.getPage(), postRequestDTO.getSize(), sort);
        } else if (postRequestDTO.getOrderby().equals("view")) {
            sort = Sort.by(Sort.Direction.DESC, "readCount");
            pageable = PageRequest.of(postRequestDTO.getPage(), postRequestDTO.getSize(), sort);
        }

        if (postRequestDTO.getCategory().equals("all")) postRequestDTO.setCategory(""); // 카테고리가 all 이면 Containing(like)를 위해 "" 처리

        // 판매방식 //
        // 1 경매, 2 즉시구매, 3 경매+즉시구매
        int sellType = 0; // not으로 쓰기 위해 뺴야할 값을 넣어줌

        switch (postRequestDTO.getSell_type()) {
            case "all" -> { sellType = 0; }
            case "auction" -> { sellType = 2; }
            case "direct" -> { sellType = 1; }
        }

        if (postRequestDTO.getSell_status().equals("all")) { // 진행상태:all
            // 정렬순: new, view
            return getPostResponseDTO(boardRepository.findSellStatusAllAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else if (postRequestDTO.getSell_status().equals("ingnend")) { // 진행상태:ingnend
            // 정렬순: new, view
            return getPostResponseDTO(boardRepository.findSellStatusIngNEndAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else if (postRequestDTO.getSell_status().equals("yetnend")) { // 진행상태:yetnend
            // 정렬순: new, view
            return getPostResponseDTO(boardRepository.findSellStatusYetNEndAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else if (postRequestDTO.getSell_status().equals("yetning")) { // 진행상태:yetning
            // 정렬순: new, view
            return getPostResponseDTO(boardRepository.findSellStatusYetNIngAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else if (postRequestDTO.getSell_status().equals("yet")) { // 진행상태:yet
            // 정렬순:new, view
            return getPostResponseDTO(boardRepository.findSellStatusYetAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else if (postRequestDTO.getSell_status().equals("ing")) { // 진행상태:ing
            // 정렬순:new, view
            return getPostResponseDTO(boardRepository.findSellStatusIngAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        } else { // 진행상태:end
            // 정렬순:new, view
            return getPostResponseDTO(boardRepository.findSellStatusEndAndNewOrView(sellType, postRequestDTO.getSearch(), postRequestDTO.getCategory(), date_now, pageable), postRequestDTO);
        }
    }
    private Page<PostResponseDTO> getPostResponseDTO (Page<Board> board, PostRequestDTO postRequestDTO) {

        return board.map(board_item -> {
            if(postRequestDTO.getMember_id() != null) {
                Long bid_count = bidRepository.countByBoardBoardNumAndBidCancelFalse(board_item.getBoardNum()); // 게시글 입찰 수 조회
                Userlike user_like = userlikeRepository.findByBoardBoardNumAndMemberMemberId(board_item.getBoardNum(), postRequestDTO.getMember_id());
                return new PostResponseDTO(board_item, bid_count, user_like);
            } else {
                int num = board_item.getBoardNum();
                Long bid_count = bidRepository.countByBoardBoardNumAndBidCancelFalse(board_item.getBoardNum()); // 게시글 입찰 수 조회
                return new PostResponseDTO(board_item, bid_count, null);
            }});
    }

    // 조회수 증가
    public void readCount(PostReadCountResquestDTO postReadCountResquestDTO) {
        boardRepository.findByBoardNumAndUpdatePlusReadCount(postReadCountResquestDTO.getBoard_num());
    }


}
