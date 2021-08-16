package com.spring.itjunior.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Data
public class RecruitDTO {
    private int recruit_idx; //pk
    private Long job_idx; //기업 unique 키
    private String company_name;  //기업명
    private String title; //공고 제목
    private String location;  //지역명
    private String job_type; //근무 형태

//    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ssZ")
    private LocalDateTime expiration_date; //마감일
    private String close_type;  //마감일 형식
    private String category; //직종
    private String experience_level; //경력(값)
    private String required_education_level; //학력(값)
    private String salary;  //급여
    private String applycnt;  //지원자 수
    private String active; //공고 진행 여부(1:진행중, 0:마감)
    private String url; //채용공고 표준 URL
    private String company_href; //기업정보 페이지(공개 되어있는 경우)
}
