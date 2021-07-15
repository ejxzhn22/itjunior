package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.domain.FreeLike;
import com.spring.itjunior.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {

    //프리게시판 게시글 모두 가져오기
    public List<FreeBoard> selectAll();

    // 게시글 하나 가져오기
    public FreeBoard selectById(int free_idx);

    // 게시글 하나 가져오기 - 카테고리 이름 포함
    public BoardDto selectOne(int free_idx);

    // 게시글 추가하기
    public int insertBoard(FreeBoard freeBoard);

    //게시글 수정하기
    public int updateBoard(FreeBoard freeBoard);

    //게시글 삭제하기
    public int deleteBoard(int free_idx);

    //카테고리 가져오기
    public List<Category> category();

    //조회수 올리기
    public int viewcntUpdate(int free_idx);

    //글 추천하기
    public int freeLike(FreeLike freeLike);

    //글 추천 취소하기
    public int deleteFreeLike(FreeLike freeLike);

    //글 추천수 가져오기
    public int viewcnt(int free_idx);
}
