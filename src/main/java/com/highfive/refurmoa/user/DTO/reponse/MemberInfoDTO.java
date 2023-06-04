package com.highfive.refurmoa.user.DTO.reponse;

import com.highfive.refurmoa.entity.Member;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberInfoDTO {
	private String name;
	private int grade;
	private int order;
	private int bid;
	private int bookmark;
	public MemberInfoDTO(Member mem,int order,int bid,int bookmark) {
		this.name=mem.getName();
		this.grade=mem.getGrade();
		this.order=order;
		this.bid=bid;
		this.bookmark=bookmark;
	}
}
