package com.spring.itjunior.domain;

import com.spring.itjunior.dto.QnaDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/*
* qna_idx int AI PK
title varchar(60)
content varchar(400)
writer varchar(45)
create_time datetime
delete_yn varchar(10)
secret_yn varchar(10)
reply_yn varchar(10)
member_idx int
* */

@Data
public class QnaBoard {

    private int qna_idx;
    private String title;
    private String content;
    private String writer;
    private LocalDateTime create_time;
    private DeleteYN delete_yn;
    private DeleteYN secret_yn;
    private String secret_pwd;
    private DeleteYN reply_yn;
    private int member_idx;


    public static QnaBoard setQnaBoard(QnaBoard qnaBoard,QnaDto qnaDto, String writer, int member_idx) {

        qnaBoard.setTitle(qnaDto.getTitle());
        qnaBoard.setContent(qnaDto.getContent());
        qnaBoard.setWriter(writer);
        qnaBoard.setDelete_yn(DeleteYN.N);
        qnaBoard.setReply_yn(DeleteYN.N);
        if(qnaDto.isSecret_yn()){
            qnaBoard.setSecret_yn(DeleteYN.Y);
        }else{
            qnaBoard.setSecret_yn(DeleteYN.N);
        }
        qnaBoard.setSecret_pwd(qnaDto.getSecret_pwd());
        qnaBoard.setMember_idx(member_idx);
        return qnaBoard;
    }
}
