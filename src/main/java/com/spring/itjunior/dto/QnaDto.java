package com.spring.itjunior.dto;

import com.spring.itjunior.domain.DeleteYN;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QnaDto {
    private int qna_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;
    private boolean secret_yn;
    private String secret_pwd;
    private DeleteYN reply_yn;
    private String answer_title;
    private String answer_content;
    private String answer_writer;
    private LocalDateTime answer_create_time;
}
