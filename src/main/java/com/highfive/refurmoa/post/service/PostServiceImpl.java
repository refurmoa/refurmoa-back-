package com.highfive.refurmoa.post.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.entity.Userlike;
import com.highfive.refurmoa.post.dto.reponse.PostInfoDTO;
import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostWriteDTO;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.repository.BidRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.post.repository.UserlikeRepository;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.repository.ProductRepository;


@Service
public class PostServiceImpl implements PostService {

    private final BoardRepository boardRepository;
    private final BidRepository bidRepository;
    private final UserlikeRepository userlikeRepository;
    private ProductRepository productrepository;
	private ProdPartnerRepository partnerRepository;

    public PostServiceImpl(BoardRepository boardRepository, BidRepository bidRepository, UserlikeRepository userlikeRepository,ProductRepository productrepository, ProdPartnerRepository partnerRepository) {
        this.boardRepository = boardRepository;
        this.bidRepository = bidRepository;
        this.userlikeRepository = userlikeRepository;
        this.productrepository =productrepository ;
        this.partnerRepository = partnerRepository;
    }
  
    @Override // 찜 등록/취소
    public void userlikeupdate(UserlikeRequestDTO userlikeDTO) {
        if (userlikeDTO.isLike() == true) {
            Userlike userlike = userlikeRepository.findByBoardBoardNumAndMemberMemberId(userlikeDTO.getBoardNum(), userlikeDTO.getMemberId());
            userlikeRepository.delete(userlike);
        } else { userlikeRepository.save(new Userlike(userlikeDTO.getMemberId(), userlikeDTO.getBoardNum())); }
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
    @Override
    public PostInfoDTO Postinfo(int board_num) {
    	Board board=boardRepository.findByBoardNum(board_num);
    	FindProductDTO tmp = new FindProductDTO(board.getProduct());
    	return new PostInfoDTO(tmp,board);
    }
    
    @Override
    public int PostWrite(MultipartFile mainImg,MultipartFile detailFile,PostWriteDTO prodDto) throws IOException{
    	
 
		String mainName = null;
		if(mainImg!=null) {
			File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			mainImg.transferTo(main);
			mainName=main.toString();
		}else {
			mainName=productrepository.MainInfo(prodDto.getProduct_code());
		}
		String detailName = null;
		if(detailFile!=null) {
			File detail = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			detailFile.transferTo(detail);
			detailName=detail.toString();
		}else {
			detailName=boardRepository.findByBoardNum(prodDto.getBoard_num()).getDetailImage();
		}
		
		ProdPartner tmp =new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(),tmp,prodDto.getCategory_code(),prodDto.getCategory(),mainName,prodDto.getProd_com(),
				prodDto.getProd_name(),prodDto.getProd_grade(),prodDto.getOrg_price(),prodDto.isGuarantee(),prodDto.getDeffect_text(),prodDto.getDeffect_image1(),
				prodDto.getDeffect_image2(),prodDto.getDeffect_image3(),prodDto.getReg_date(),1);	
		insertProd(prodDto.getCom_num(),productEntity);
		productrepository.save(productEntity);
		
		
		
		Board board= new Board(productEntity,prodDto,detailName);
		boardRepository.save(board);
		
		return productEntity.getProductCode();
    }
    public void insertProd(int comNum,Product prod) {
		ProdPartner partner=partnerRepository.findById(comNum).orElse(null);
		prod.setProdPartner(partner);
}
    
}
