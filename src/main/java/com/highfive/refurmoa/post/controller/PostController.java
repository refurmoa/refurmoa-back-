package com.highfive.refurmoa.post.controller;


import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.post.dto.PostReadCountResquestDTO;
import com.highfive.refurmoa.post.dto.PostRequestDTO;
import com.highfive.refurmoa.post.dto.PostResponseDTO;
import com.highfive.refurmoa.post.service.PostServiceImpl;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.awt.print.Pageable;
import java.util.List;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postServiceImpl;

    public PostController(PostServiceImpl postServiceImpl) {
        this.postServiceImpl = postServiceImpl;
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

}
