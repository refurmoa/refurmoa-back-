package com.highfive.refurmoa.post.controller;

import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.service.PostServiceImpl;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post")
public class PostController {

    private final PostServiceImpl postService;

    // 찜 등록/취소
    @PostMapping("/like")
    public void userlikeupdate(@RequestBody UserlikeRequestDTO userlikeDTO) {
        postService.userlikeupdate(userlikeDTO);
    }

}
