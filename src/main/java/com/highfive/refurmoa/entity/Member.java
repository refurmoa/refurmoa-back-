package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member {

    @Id
    @Column(name = "member_id", nullable = false, length = 15)
    private String memberId;

    @Column(nullable = false, length = 20)
    private String password;

    @Column(nullable = false, length = 10)
    private String name;

    @Column(nullable = false, length = 15)
    private String phone;

    @Column(nullable = false, length = 30)
    private String email;

    @Column(nullable = false, length = 50)
    private String address;

    @Column(name = "detail_address", nullable = false, length = 50)
    private String detailAddress;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date birth;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int grade;

    @Column(columnDefinition = "int default 0", nullable = false)
    private int mile;

    @Column(name = "accept_location", nullable = false)
    private boolean acceptLocation;

    @Column(name = "accept_alarm", nullable = false)
    private boolean acceptAlarm;

}