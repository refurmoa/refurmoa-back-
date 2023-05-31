package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import java.util.Date;

@Getter
@Setter
@Entity
public class Inquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private int num;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Member memberId;

    @Column(name = "inq_title", nullable = false, length = 20)
    private String inqTitle;

    @Column(name = "inq_con", nullable = false, length = 500)
    private String inqCon;

    @Column(name = "inq_img", length = 100)
    private String inqImg;
    
    @Column(name = "inq_org_img", length = 100)
    private String inqOrgImg;

    @Column(name = "inq_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date inqDate;

    @Column(name = "answer_con", length = 500)
    private String answerCon;

    @Column(name = "answer_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date answerDate;

}