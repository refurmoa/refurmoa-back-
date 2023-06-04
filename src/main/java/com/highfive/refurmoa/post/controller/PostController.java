package com.highfive.refurmoa.post.controller;

import java.io.IOException;

import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostWriteDTO;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.service.PostServiceImpl;
import com.highfive.refurmoa.prod.service.ProductServiceImpl;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;
    
    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
   
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
    @PostMapping("/write")
    public int PostWrite(@RequestParam(value="main_image",required = false) MultipartFile mainImg,@RequestParam(value="detailFile",required = false) MultipartFile detailFile,PostWriteDTO postDto) throws IllegalStateException, IOException  {

        return postServiceImpl.PostWrite(mainImg,detailFile,postDto);
    }

}
