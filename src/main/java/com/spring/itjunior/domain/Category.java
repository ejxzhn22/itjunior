package com.spring.itjunior.domain;

/*
Columns:
        name varchar(45)
        cate_no int PK
        cate_code int
*/

import lombok.Data;

@Data
public class Category {
    private String name;
    private int cate_no;
    private int cate_code;
}
