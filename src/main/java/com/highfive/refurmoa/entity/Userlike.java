package com.highfive.refurmoa.entity;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Userlike {
	
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "like_num", nullable = false)
	  	private int likeNum;
	  	
	  	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "member_id")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Member memberId;
	  	
	  	@ManyToOne(fetch = FetchType.LAZY)
	    @JoinColumn(name = "board_num")
	    @OnDelete(action = OnDeleteAction.CASCADE)
	    private Board boardNum;
	  	
}
