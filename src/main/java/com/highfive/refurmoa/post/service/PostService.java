package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.dto.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.PostRequestDTO;
import com.highfive.refurmoa.post.dto.PostResponseDTO;
import org.springframework.data.domain.Page;

public interface PostService {
    Page<PostResponseDTO> getPostList(PostRequestDTO postRequestDTO);
    void readCount(PostReadCountResquestDTO postReadCountResquestDTO);
}
