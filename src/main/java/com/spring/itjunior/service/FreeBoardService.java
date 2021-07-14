package com.spring.itjunior.service;

import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.domain.FreeLike;
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

    //카테고리 가져오기
    public List<Category> category(){
        return freeBoardMapper.category();
    }

    //조회수 올리기
    public int viewUpdate(int free_idx) {
        return freeBoardMapper.viewcntUpdate(free_idx);
    }

    //글 추천하기 -> 회원 받아서 넣기
    public int freeLike(int free_idx){
        FreeLike freeLike = new FreeLike();

        return freeBoardMapper.freeLike(freeLike);
    }

    //글 추천 삭제하기 -> 회원 받아서 넣기
    public int deleteFreeLike(int free_idx){
        FreeLike freeLike = new FreeLike();

        return freeBoardMapper.deleteFreeLike(freeLike);
    }


}
