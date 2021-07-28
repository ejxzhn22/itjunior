package com.spring.itjunior.domain;

import com.spring.itjunior.dto.PageDto;
import lombok.*;

import java.time.LocalDateTime;

/*
free_idx int AI PK
title varchar(200)
content varchar(400)
writer varchar(45)
create_time datetime
update_time datetime
delete_time datetime
delete_yn enum('Y','N')
category varchar(20)
viewcnt int
replycnt int
likecnt int
member_idx int
 */

@Data
public class FreeBoard  extends PageDto {

    private int free_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;
    private LocalDateTime update_time;
    private LocalDateTime delete_time;
    private DeleteYN delete_yn;
    private int category;
    private int viewcnt;
    private int replycnt;
    private int member_idx;
    private int notice_pin;

}
