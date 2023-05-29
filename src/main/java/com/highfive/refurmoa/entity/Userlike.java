package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Userlike {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "like_num", nullable = false)
	private int likeNum;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "member_id", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Member member;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "board_num", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private Board board;

	public Userlike(int boardNum, String memberId) {
		this.member = new Member();
		this.member.setMemberId(memberId);
		this.board = new Board();
		this.board.setBoardNum(boardNum);
	}

}
