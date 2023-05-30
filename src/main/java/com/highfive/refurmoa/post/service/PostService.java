package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;

public interface PostService {

    void userlikeupdate(UserlikeRequestDTO userlikeDTO); // 찜 등록/삭제

}
