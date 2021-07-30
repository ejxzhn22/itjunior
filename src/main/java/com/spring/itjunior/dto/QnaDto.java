package com.spring.itjunior.dto;

import com.spring.itjunior.domain.DeleteYN;
import com.spring.itjunior.domain.QnaBoard;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class QnaDto {
    private int qna_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;
    private boolean secret_yn;
    private String secret_pwd;
    private DeleteYN reply_yn;
    private String answer_title;
    private String answer_content;
    private String answer_writer;
    private LocalDateTime answer_create_time;

    public static QnaDto setDto(QnaDto qnaDto, String title, String content, String writer,
                         int qna_idx, LocalDateTime create_time, DeleteYN reply_yn, DeleteYN secret_yn){
        qnaDto.setTitle(title);
        qnaDto.setContent(content);
        qnaDto.setWriter(writer);
        qnaDto.setQna_idx(qna_idx);
        qnaDto.setCreate_time(create_time);
        qnaDto.setReply_yn(reply_yn);
        if(secret_yn == DeleteYN.Y){
            qnaDto.setSecret_yn(true);
        }else {
            qnaDto.setSecret_yn(false);
        }

        return qnaDto;
    }

    public static QnaDto setAnswerDto(QnaDto qnaDto,String answer_title, String answer_content,
                               String answer_writer, LocalDateTime answer_create_time){
        qnaDto.setAnswer_title(answer_title);
        qnaDto.setAnswer_content(answer_content);
        qnaDto.setAnswer_writer(answer_writer);
        qnaDto.setAnswer_create_time(answer_create_time);

        return qnaDto;
    }


}
