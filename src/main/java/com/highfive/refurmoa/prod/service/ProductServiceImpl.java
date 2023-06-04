package com.highfive.refurmoa.prod.service;

import java.io.File;
import java.io.IOException;
import java.util.*;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.prod.DTO.response.ProdListResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.admin.dto.response.PartnerDTO;
import com.highfive.refurmoa.admin.repository.ProdPartnerRepository;
import com.highfive.refurmoa.entity.ProdPartner;
import com.highfive.refurmoa.entity.Product;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.request.ProdResponseDTO;
import com.highfive.refurmoa.prod.DTO.request.ProductWriteDTO;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdListDTO;
import com.highfive.refurmoa.prod.DTO.response.ProdSearchDTO;
import com.highfive.refurmoa.prod.repository.ProductRepository;

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




	private HashMap<String ,String> map= new HashMap<String ,String>(){{//초기값 지정
		put("all","01234");
		put("ingnend","1234");
		put("yetnend","04");
		put("yetning","0123");
		put("yet","0");
		put("ing","123");
		put("end","4");
	}};
	@Override
	 public List<ProdListDTO> productSearch(ProdSearchDTO body){
		 String cate="";
		 switch(body.getCategory()) {
			 case "all": cate="";
			 break;
			 case "furniture": cate= "fur" ;
			 break;
			 case  "appliance": cate= "app";
			 break;
			 default: cate=body.getCategory();	 
		 }

		 Date now=new Date();
		 List<Product> prodlist=repository.findProdList(body.getSearchword(),cate);
		 List<ProdListDTO> temp =new ArrayList<ProdListDTO>();
		 	 
		 for(int i=0;i<prodlist.size();i++) {
			 int sell_status=0;
			 Date[] date=repository.getDate(prodlist.get(i).getProductCode());
			 if(prodlist.get(i).getProdState()==1) {
				 if(now.compareTo(date[0])==1)sell_status=1;
					else sell_status=2;
			 }
			 else if(prodlist.get(i).getProdState()==2)sell_status=3;
			 else if(prodlist.get(i).getProdState()==5)sell_status=4;
			 if(map.get(body.getSellstatus()).contains(Integer.toString(sell_status))) temp.add(new ProdListDTO(prodlist.get(i),sell_status));
		 }return temp;
	 }
	 @Override
	 public List<ProdListDTO> productList(String category,String status) {
		  
		 String cate="";
		 switch(category) {
			 case "all": cate="";
			 break;
			 case "furniture": cate= "fur" ;
			 break;
			 case  "appliance": cate= "app";
			 break;
			 default: cate=category;	 
		 }
		 Date now=new Date();
		 List<Product> prodlist=repository.findProduct(cate);
		 List<ProdListDTO> temp =new ArrayList<ProdListDTO>();			 
		 for(int i=0;i<prodlist.size();i++) {
			 int sell_status=0;
			 Date[] date=repository.getDate(prodlist.get(i).getProductCode());
			 if(prodlist.get(i).getProdState()==1) {
				 if(now.compareTo(date[0])==1)sell_status=1;
				else sell_status=2;
			 }
			 else if(prodlist.get(i).getProdState()==2)sell_status=3;
			 else if(prodlist.get(i).getProdState()==5)sell_status=4;			 
			if(map.get(status).contains(Integer.toString(sell_status))) temp.add(new ProdListDTO(prodlist.get(i),sell_status));						
		 }	 
		 return temp;
	 }
	 
	@Override
	public int productDelete(int code) {
		repository.deleteById(code);
		return 1;
	}
	@Override
	public void insertProd(int comNum,Product prod) {
			ProdPartner partner=partnerRepository.findById(comNum).orElse(null);
			prod.setComNum(partner);		
	}
	@Override
	public int ProductUpdate(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException {
		String mainName = null;
		if(mainImg.getSize()!=0) {
			File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
			mainImg.transferTo(main);
			mainName=main.toString();
		}else {
			mainName=repository.MainInfo(prodDto.getProduct_code());
		}
		ProdPartner tmp =new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(),tmp,prodDto.getCategory_code(),prodDto.getCategory(),mainName,prodDto.getProd_com(),
				prodDto.getProd_name(),prodDto.getProd_grade(),prodDto.getOrg_price(),prodDto.isGuarantee(),prodDto.getDeffect_text(),prodDto.getDeffect_image1(),
				prodDto.getDeffect_image2(),prodDto.getDeffect_image3(),prodDto.getReg_date(),prodDto.getProd_state());	
		
		insertProd(prodDto.getCom_num(),productEntity);
        repository.save(productEntity);
		return productEntity.getProductCode();
	}
	@Override
	public int ProductWrite(MultipartFile mainImg,ProductWriteDTO prodDto) throws IOException {
		
		File main = new File("prod\\"+UUID.randomUUID().toString().replaceAll("-", "")+".jpg");
		mainImg.transferTo(main);

		ProdPartner tmp =new ProdPartner();
		Product productEntity = new Product(prodDto.getProduct_code(),tmp,prodDto.getCategory_code(),prodDto.getCategory(),main.toString(),prodDto.getProd_com(),
				prodDto.getProd_name(),prodDto.getProd_grade(),prodDto.getOrg_price(),prodDto.isGuarantee(),prodDto.getDeffect_text(),prodDto.getDeffect_image1(),
				prodDto.getDeffect_image2(),prodDto.getDeffect_image3(),prodDto.getReg_date(),prodDto.getProd_state());	
		insertProd(prodDto.getCom_num(),productEntity);
        repository.save(productEntity);
		return productEntity.getProductCode();
	}
	
	@Override
	public void insertFile(ProdFileDTO dto) {
		repository.insert(dto.getProdNum(),dto.getUp_deffect_image1(),dto.getUp_deffect_image2(),dto.getUp_deffect_image3());
	}
	@Override
	public ProdResponseDTO productInfo(int productCode){
		Product tmp=repository.findById(productCode).orElse(null);
		ProdResponseDTO dto =new ProdResponseDTO(tmp);
		return dto;
	}
	@Override
	public Page<FindProductDTO> findProduct(String search,Pageable pageable){
		Page<Product> tmp=repository.findProduct(search,pageable);
		   Page<FindProductDTO> Page =tmp.map(partner->{
			   return new FindProductDTO(partner);	
		   });
		   return Page;		
	}
}
