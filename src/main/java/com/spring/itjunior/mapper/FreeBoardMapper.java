package com.spring.itjunior.mapper;

import com.spring.itjunior.domain.FreeBoard;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FreeBoardMapper {

    //프리게시판 게시글 모두 가져오기
    public List<FreeBoard> selectAll();

    // 게시글 하나 가져오기
    public FreeBoard selectById(int free_idx);

    // 게시글 추가하기
    public int insertBoard(FreeBoard freeBoard);

    //게시글 수정하기
    public int updateBoard(FreeBoard freeBoard);

    //게시글 삭제하기
    public int deleteBoard(FreeBoard freeBoard);
}
