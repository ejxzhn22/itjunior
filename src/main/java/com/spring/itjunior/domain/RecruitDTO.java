package com.spring.itjunior.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RecruitDTO {
    private int recruit_idx;
    private Long job_idx;
    private String company_name;
    private String location;
    private String experience;
    private LocalDateTime close_date;
    private String close_type;
    private String category;
    private String salary;
    private String job_type;
    private String required_edu;
    private String url;
    private int applycnt;
    private int likecnt;
}
