package com.spring.itjunior.dto;

import lombok.Data;

@Data
public class BoardDto {
    private String title;
    private String content;
    private int category;
}
