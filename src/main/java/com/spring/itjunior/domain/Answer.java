package com.spring.itjunior.domain;

import lombok.Data;

import java.time.LocalDateTime;

/*
answer_idx int AI PK
qna_idx int
content varchar(400)
writer varchar(45)
create_time datetime
 */
@Data
public class Answer {
    private int answer_idx;
    private int qna_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;

}
