package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.dto.request.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.request.PostRequestDTO;
import com.highfive.refurmoa.post.dto.reponse.PostResponseDTO;
import org.springframework.data.domain.Page;

public interface PostService {
  
    void userlikeupdate(UserlikeRequestDTO userlikeDTO); // 찜 등록/삭제
    // 판매글 목록 가져오기
    Page<PostResponseDTO> getPostList(PostRequestDTO postRequestDTO);
    // 조회수 증가
    void readCount(PostReadCountResquestDTO postReadCountResquestDTO);
  
}
