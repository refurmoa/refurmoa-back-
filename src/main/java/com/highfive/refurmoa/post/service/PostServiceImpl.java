package com.highfive.refurmoa.post.service;

import com.highfive.refurmoa.entity.Userlike;
import com.highfive.refurmoa.post.dto.request.UserlikeRequestDTO;
import com.highfive.refurmoa.post.repository.UserlikeRepository;

import org.springframework.stereotype.Service;

@Service
public class PostServiceImpl implements PostService {

    private final UserlikeRepository userlikeRepository;

    public PostServiceImpl(UserlikeRepository userlikeRepository) {
        this.userlikeRepository = userlikeRepository;
    }

    @Override // 찜 등록/취소
    public int userlikeupdate(UserlikeRequestDTO userlikeDTO) {
        try {
            if (userlikeDTO.isLike() == true) {
                Userlike userlike = userlikeRepository.findByBoardBoardNumAndMemberMemberId(userlikeDTO.getBoardNum(), userlikeDTO.getMemberId());
                userlikeRepository.delete(userlike);
            } else { userlikeRepository.save(new Userlike(userlikeDTO.getMemberId(), userlikeDTO.getBoardNum())); }
            return 1;
        } catch (Exception e) { return 0; }
    }

}