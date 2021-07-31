package com.spring.itjunior.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
reply_idx int AI PK
free_idx int
member_idx int
content varchar(300)
writer varchar(45)
create_time datetime
parent_idx int
depth int
order int
emoji varchar(45)
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "replyBuild")
@Data
public class Reply {
    private int reply_idx;
    private int free_idx;
    private int member_idx;
    private String emoji;
    private String content;
    private String writer;
    private LocalDateTime create_time;
    private int parent_idx;
    private int depth;
    private int reply_order;
    private int like_count;
    private int unlike_count;
    private boolean like_state;
    private boolean unlike_state;

    public static ReplyBuilder builder(int free_idx, int member_idx, String writer, String content,
                                       String emoji, int parent_idx, int reply_order){
        return replyBuild()
                .free_idx(free_idx)
                .member_idx(member_idx)
                .writer(writer)
                .content(content)
                .emoji(emoji)
                .parent_idx(parent_idx)
                .reply_order(reply_order);
    }
}
