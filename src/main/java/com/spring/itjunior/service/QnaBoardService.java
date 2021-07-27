package com.spring.itjunior.service;

import com.spring.itjunior.domain.Answer;
import com.spring.itjunior.domain.QnaBoard;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.mapper.QnaBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class QnaBoardService {

    private final QnaBoardMapper qnaBoardMapper;

    //게시글 모두 가져오기
    public List<QnaBoard> qnaBoards (PageDto pageDto) {
        return qnaBoardMapper.selectAll(pageDto);
    }

    //게시글 하나 가져오기
    public QnaBoard qnaBoard (int qna_idx){
        return qnaBoardMapper.selectOne(qna_idx);
    }

    //게시글 갯수
    public int selectBoardTotalCount(PageDto pageDto) {
        return qnaBoardMapper.selectBoardTotalCount(pageDto);
    }

    //게시글 추가
    public int newQna(QnaBoard qnaBoard){
        return qnaBoardMapper.insertQna(qnaBoard);
    }

    //답변 가져오기
    public Answer answer(int qna_idx){
        return qnaBoardMapper.selectAnswer(qna_idx);
    }
}
