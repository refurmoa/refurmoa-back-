package com.highfive.refurmoa.prod.service;

import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;

import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListResponseDTO;
import com.highfive.refurmoa.prod.repository.ProductRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
	
	 private ProductRepository repository;
	 private BoardRepository boardRepository;
	 private ProdPartnerRepository partnerRepository;

	 public ProductServiceImpl(ProductRepository repository, BoardRepository boardRepository, ProdPartnerRepository partnerRepository) {
	        this.repository = repository;
			this.boardRepository = boardRepository;
	        this.partnerRepository = partnerRepository;
	 }

	@Override // 상품 목록 조회
	public Page<ProdListResponseDTO> productList(String search, String category, String status, Pageable pageable) {
		List<Integer> statusList = switch (status) {
			case "ingnend" -> Arrays.asList(1, 2, 3, 4, 5);
			case "yetnend" -> Arrays.asList(0, 5);
			case "yetning" -> Arrays.asList(0, 1, 2, 3, 4);
			case "yet" -> List.of(0);
			case "ing" -> Arrays.asList(1, 2, 3, 4);
			case "end" -> List.of(5);
			default -> Arrays.asList(0, 1, 2, 3, 4, 5);
		};
		if (category == "all") category = "";
		Page<Product> productList = repository.getListProduct(search, category, statusList, pageable);
		return productList.map(product -> {
			Date startDate = null;
			String comName = partnerRepository.findByComNum(product.getComNum().getComNum()).getComName();
			if (product.getProdState() == 1) {
				List<Board> board = boardRepository.findByProductProductCodeAndDeleteCheckFalseOrderByBoardNumDesc(product.getProductCode());
				if (board != null && !board.isEmpty()) startDate = board.get(0).getStartDate();
				return new ProdListResponseDTO(product, startDate, comName);
			}
			return new ProdListResponseDTO(product, null, comName);
		});
	}

	@Override // 상품 삭제
	public int productDelete(int code) {
		repository.deleteById(code);
		return 1;
	}

	@Override // 상품 등록
	public void insertProd(int comNum,Product prod) {
		ProdPartner partner=partnerRepository.findById(comNum).orElse(null);
		prod.setComNum(partner);
	}

	@Override // 상품 수정
	public int ProductUpdate(MultipartFile mainImg, ProductWriteDTO prodDto) throws IOException {
		String mainName = null;
		if (mainImg.getSize()!=0) {
			File main = new File("prod\\"+ UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			mainImg.transferTo(main);
			mainName = main.toString();
		} else {
			mainName = repository.MainInfo(prodDto.getProduct_code());
		}
		ProdPartner tmp = new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(),tmp,prodDto.getCategory_code(),prodDto.getCategory(),mainName,prodDto.getProd_com(),
				prodDto.getProd_name(),prodDto.getProd_grade(),prodDto.getOrg_price(),prodDto.isGuarantee(),prodDto.getDeffect_text(),prodDto.getDeffect_image1(),
				prodDto.getDeffect_image2(),prodDto.getDeffect_image3(),prodDto.getReg_date(),prodDto.getProd_state());	
		
		insertProd(prodDto.getCom_num(),productEntity);
        repository.save(productEntity);
		return productEntity.getProductCode();
	}

	@Override // 상품 등록
	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException {
		File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		mainImg.transferTo(main);

		ProdPartner tmp = new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(), tmp, prodDto.getCategory_code(), prodDto.getCategory(), main.toString(), prodDto.getProd_com(),
				prodDto.getProd_name(), prodDto.getProd_grade(), prodDto.getOrg_price(), prodDto.isGuarantee(), prodDto.getDeffect_text(), prodDto.getDeffect_image1(),
				prodDto.getDeffect_image2(), prodDto.getDeffect_image3(), prodDto.getReg_date(), prodDto.getProd_state());
		insertProd(prodDto.getCom_num(),productEntity);
        repository.save(productEntity);
		return productEntity.getProductCode();
	}
	
	@Override // 상품 이미지 저장
	public void insertFile(ProdFileDTO dto) {
		repository.insert(dto.getProdNum(),dto.getUp_deffect_image1(),dto.getUp_deffect_image2(),dto.getUp_deffect_image3());
	}

	@Override // 상품 정보 조회
	public ProdResponseDTO productInfo(int productCode) {
		Product tmp = repository.findById(productCode).orElse(null);
		ProdResponseDTO dto = new ProdResponseDTO(tmp);
		return dto;
	}

}
