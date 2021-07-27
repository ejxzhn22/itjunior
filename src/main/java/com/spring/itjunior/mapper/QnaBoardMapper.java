package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QnaBoardMapper {

    //큐앤에이 게시글 모두 가져오기
    public List<QnaBoard> selectAll(PageDto pageDto);

    //큐앤에이 게시글 갯수
    public int selectBoardTotalCount(PageDto pageDto);

    //큐앤에이 추가
    public int insertQna(QnaBoard qnaBoard);

    //큐엔에이 하나 가져오기
    public QnaBoard selectOne(int qna_idx);

    //답변 가져오기
    public Answer selectAnswer(int qna_idx);
}
