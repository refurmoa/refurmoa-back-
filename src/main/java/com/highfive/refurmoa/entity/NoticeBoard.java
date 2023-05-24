package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@Table(name = "notice_board")
@Entity
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noti_num", nullable = false)
    private int notiNum;

    @Column(name = "noti_title", nullable = false)
    private String notiTitle;

    @Column(name = "noti_inf", nullable = false)
    private String notiInf;

    @Column(name = "noti_file1")
    private String notiFile1;

    @Column(name = "noti_file2")
    private String notiFile2;

    @Column(name = "read_count", nullable = false)
    private int readCount;

    @Column(name = "noti_date", nullable = false)
    private Date notiDate;
}
