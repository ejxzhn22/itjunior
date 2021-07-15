package com.spring.itjunior.dto;

import lombok.Data;

@Data
public class BoardDto {
    private int free_idx;
    private String title;
    private String content;
    private String writer;
    private int category;
    private String cate_name;
    private int viewcnt;
    private int likecnt;
}
