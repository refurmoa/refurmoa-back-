package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "notice_board")
public class NoticeBoard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "noti_num", nullable = false)
    private int notiNum;

    @Column(name = "noti_title", nullable = false, length = 20)
    private String notiTitle;

    @Column(name = "noti_inf", nullable = false, length = 50)
    private String notiInf;

    @Column(name = "noti_image", length = 100)
    private String notiImage;

    @Column(name = "read_count", nullable = false)
    private int readCount;

    @Column(name = "noti_date", nullable = false)
    private Date notiDate;

}
