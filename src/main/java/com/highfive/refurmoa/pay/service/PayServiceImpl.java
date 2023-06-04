package com.highfive.refurmoa.pay.service;

import com.highfive.refurmoa.entity.Board;
import com.highfive.refurmoa.entity.Member;
import com.highfive.refurmoa.pay.dto.response.PayInfoResponseDTO;
import com.highfive.refurmoa.pay.dto.response.UserInfoResponseDTO;
import com.highfive.refurmoa.pay.repository.PayRepository;
import com.highfive.refurmoa.post.repository.BoardRepository;
import com.highfive.refurmoa.user.repository.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class PayServiceImpl implements PayService {

    private final PayRepository payRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public PayServiceImpl(PayRepository payRepository, BoardRepository boardRepository, MemberRepository memberRepository) {
        this.payRepository = payRepository;
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    @Override // 결제(상품)정보 조회
    public PayInfoResponseDTO getPayInfo(int boardNum, int sellType) {
        Board board = boardRepository.findByBoardNumAndDeleteCheckFalse(boardNum);
        return new PayInfoResponseDTO(board, sellType);
    }

    @Override // 회원 정보 조회
    public UserInfoResponseDTO getUserInfo(String memberId) {
        Member member = memberRepository.findByMemberId(memberId);
        return new UserInfoResponseDTO(member);
    }
}
