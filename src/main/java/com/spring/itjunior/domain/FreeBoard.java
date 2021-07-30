package com.spring.itjunior.domain;

import com.spring.itjunior.dto.PageDto;
import lombok.*;

import java.security.cert.CertPathBuilder;
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

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "freeBoardBuild")
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


    public static FreeBoardBuilder builder(int member_idx, String writer,
                                    String title, String content, DeleteYN delete_yn, int category){
            int notice_pin = 0;
            if(category == 301){
                notice_pin = 1;
            }

        return freeBoardBuild()
                .member_idx(member_idx)
                .writer(writer)
                .title(title)
                .content(content)
                .delete_yn(delete_yn)
                .category(category)
                .notice_pin(notice_pin);
    }

}
