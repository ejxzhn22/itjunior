package com.spring.itjunior.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BoardDto extends PageDto {
    private int free_idx;
    private int member_idx;
    private String title;
    private String content;
    private String writer;
    private int category;
    private String cate_name;
    private LocalDateTime create_time;
    private int viewcnt;
    private int likecnt;
    private int replycnt;
    private boolean likeState;
}
