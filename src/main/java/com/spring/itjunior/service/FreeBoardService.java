package com.spring.itjunior.service;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.domain.FreeLike;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.mapper.FreeBoardMapper;
import com.spring.itjunior.paging.Criteria;
import com.spring.itjunior.paging.PaginationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class FreeBoardService {

    private final FreeBoardMapper freeBoardMapper;

    //모든 게시글 가져오기 /
    public List<FreeBoard> boards(PageDto pageDto) {

        return freeBoardMapper.selectAll(pageDto);

    }

    //게시글 개수 가져오기
    public int selectBoardTotalCount() {
        return freeBoardMapper.selectBoardTotalCount();
    }

    //게시글 하나 가져오기/
    public FreeBoard board(int free_idx){
        return freeBoardMapper.selectById(free_idx);
    }

    //게시글 하나 가져오기 -> 카테고리 이름 포함
    public BoardDto selectBoard(int free_idx){
        return freeBoardMapper.selectOne(free_idx);
    }

    //게시글 추가/
    public int newBoard(FreeBoard freeBoard) {
        return freeBoardMapper.insertBoard(freeBoard);
    }

    //게시글 수정/
    public int updateBaord(FreeBoard freeBoard){
        return freeBoardMapper.updateBoard(freeBoard);
    }

    //게시글 삭제
    public int deleteBoard(int free_idx){
        return freeBoardMapper.deleteBoard(free_idx);
    }

    //카테고리 가져오기
    public List<Category> category(){
        return freeBoardMapper.category();
    }

    //조회수 올리기
    public int viewUpdate(int free_idx) {
        return freeBoardMapper.viewcntUpdate(free_idx);
    }

    //글 추천하기
    public int freeLike(FreeLike freeLike){

        return freeBoardMapper.freeLike(freeLike);
    }

    //글 추천 삭제하기
    public int deleteFreeLike(FreeLike freeLike){

        return freeBoardMapper.deleteFreeLike(freeLike);
    }

    //글 추천수 가져오기
    public int viewcnt(int free_idx){
        return freeBoardMapper.viewcnt(free_idx);
    }

    //글 추천 여부 가져오기
    public boolean likeState(FreeLike freeLike) {
        int likeState = freeBoardMapper.likeState(freeLike);

        if(likeState == 1){
            return true;
        }else{
            return false;
        }
    }


}
