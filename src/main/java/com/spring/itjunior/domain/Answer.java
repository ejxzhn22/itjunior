package com.spring.itjunior.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/*
answer_idx int AI PK
qna_idx int
content varchar(400)
writer varchar(45)
create_time datetime
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderMethodName = "answerBuild")
@Data
public class Answer {
    private int answer_idx;
    private int qna_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;

    public static AnswerBuilder builder(String title,String content, int qna_idx){
        return answerBuild()
                .title(title)
                .content(content)
                .qna_idx(qna_idx);
    }
}
