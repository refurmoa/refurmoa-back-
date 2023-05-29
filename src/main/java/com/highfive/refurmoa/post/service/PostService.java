package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.post.dto.UserlikeRequestDTO;

public interface PostService {

    int userlikeupdate(UserlikeRequestDTO userlikeDTO); // 찜 등록/삭제

}
