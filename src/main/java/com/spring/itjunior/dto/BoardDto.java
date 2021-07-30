package com.spring.itjunior.dto;

import lombok.*;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "boardDtoBuild")
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

    public static BoardDtoBuilder builder(int likecnt, String title, boolean likeState, int replycnt){
        return boardDtoBuild()
                .likecnt(likecnt)
                .title(title)
                .likeState(likeState)
                .replycnt(replycnt);

    }

    public void setDtoTitle(String title, String cate_name) {
        title = title.replace("["+cate_name+"]", "");
        this.title = title;

    }
}
