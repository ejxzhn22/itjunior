package com.spring.itjunior.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReplyDto {
    private int reply_idx;
    private int free_idx;
    private int member_idx;
    private String content;
    private String writer;
    private String emoji;
    private LocalDateTime create_time;
    private int parent_idx;
    private int depth;
    private int reply_order;
}
