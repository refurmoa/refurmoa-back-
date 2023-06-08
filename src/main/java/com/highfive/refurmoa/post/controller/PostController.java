package com.highfive.refurmoa.post.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.post.dto.reponse.PostInfoDTO;
import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostWriteDTO;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.service.PostServiceImpl;
import com.highfive.refurmoa.prod.DTO.request.ProdFileDTO;
import com.highfive.refurmoa.prod.DTO.response.FindProductDTO;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    public PostController(PostServiceImpl postServiceImpl,ProductServiceImpl productServiceImpl) {
        this.postServiceImpl = postServiceImpl;
        this.productServiceImpl=productServiceImpl;
    }
  
    @Value("${spring.servlet.multipart.location}")
    String imageDir;
    private String saveImage(MultipartFile imageFile) throws IOException {
        String imgName = UUID.randomUUID() + "." +StringUtils.getFilename(imageFile.getOriginalFilename());
        File file = new File(imageDir + "prod\\" + imgName);
        imageFile.transferTo(file);
        return imgName;
    }
  
    // 찜 등록/취소
    @PostMapping("/like")
    public void userlikeupdate(@RequestBody UserlikeRequestDTO userlikeDTO) {
        postServiceImpl.userlikeupdate(userlikeDTO);
    }

    // 판매글 목록 가져오기
    @PostMapping("")
    public Page<PostResponseDTO> getPostList(@RequestBody PostRequestDTO postRequestDTO) {
        return postServiceImpl.getPostList(postRequestDTO);
    }

    // 조회수 증가
    @PostMapping("/readcount")
    public void readCount(@RequestBody PostReadCountResquestDTO postReadCountResquestDTO) {
        postServiceImpl.readCount(postReadCountResquestDTO);
    }
    
    @GetMapping("/update/info")
    public PostInfoDTO Postinfo(@RequestParam(value="num") int board_num){
    	return postServiceImpl.Postinfo(board_num);
    }
    private int prodNum;
  
    @PostMapping("/write")
    public int PostWrite(@RequestParam(value="main_image",required = false) MultipartFile mainImg,@RequestParam(value="detailFile",required = false) MultipartFile detailFile,PostWriteDTO postDto) throws IllegalStateException, IOException  {
    	prodNum=postServiceImpl.PostWrite(mainImg,detailFile,postDto);
    	return prodNum;
    }
  
    @PostMapping("/update")
    public int PostUpdate(@RequestParam(value="main_image",required = false) MultipartFile mainImg,@RequestParam(value="detailFile",required = false) MultipartFile detailFile,PostWriteDTO postDto) throws IllegalStateException, IOException  {
    	prodNum=postServiceImpl.PostWrite(mainImg,detailFile,postDto);
    	return prodNum;
    }
  
    @PostMapping("/file")
	public int upload(@RequestBody(required = false) MultipartFile[] uploadfiles) throws IOException {
       
		int prod_num = prodNum;
		String[] tmp=new String[3];
		for (int i=0;i<uploadfiles.length;i++) {
			tmp[i]=saveImage(uploadfiles[i]);
		}
		ProdFileDTO dto= new ProdFileDTO(prod_num,tmp[0],tmp[1],tmp[2]);
		productServiceImpl.insertFile(dto);
		return 1;
  }
  
    //판매글에서 상품 찾기
    @GetMapping("/prod-search")
	 public Page<FindProductDTO> findProduct(@RequestParam(value="search") String search, Pageable pageable) {
		return productServiceImpl.findProduct(search,pageable);
	}
    
    //상품 목록에서 판매글 작성으로
    @GetMapping("/write/prod")
	 public FindProductDTO PostProdWrite(@RequestParam(value="prod") int prod) {
    	System.out.println(prod);
		return postServiceImpl.PostProdWrite(prod);
	}
}
