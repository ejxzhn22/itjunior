package com.spring.itjunior.service;

import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.mapper.FreeBoardMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    //모든 게시글 가져오기
    public List<FreeBoard> boards() {
        return freeBoardMapper.selectAll();
    }

    //게시글 하나 가져오기
    public FreeBoard board(int free_idx){
        return freeBoardMapper.selectById(free_idx);
    }

    //게시글 추가
    public int newBoard(FreeBoard freeBoard) {

        return freeBoardMapper.insertBoard(freeBoard);
    }

    //게시글 수정
    public int updateBaord(FreeBoard freeBoard){
        return freeBoardMapper.updateBoard(freeBoard);
    }



}
