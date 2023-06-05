package com.highfive.refurmoa.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Memo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "memo_num", nullable = false)
    private int memoNum;

    @Column(name = "memo_content", nullable = false, length = 200)
    private String memoContent;

    public Memo(String content) {
        this.memoContent = content;
    }
}
