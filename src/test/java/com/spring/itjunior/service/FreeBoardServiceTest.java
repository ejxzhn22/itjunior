package com.spring.itjunior.service;

import com.spring.itjunior.domain.DeleteYN;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.paging.Criteria;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FreeBoardServiceTest {

    @Autowired private FreeBoardService freeBoardService;

//    @Test
//    public void 게시글추가하기() {
//        FreeBoard freeBoard = new FreeBoard();
//        freeBoard.setFree_idx(1);
//        freeBoard.setTitle("질문합니다");
//        freeBoard.setContent("닭발은 맛잇나용?");
//        freeBoard.setWriter("수진");
//        freeBoard.setCategory(103);
//        freeBoard.setMember_idx(1);
//        freeBoard.setDelete_yn(DeleteYN.N);
//
//        int result = freeBoardService.newBoard(freeBoard);
//
//        Assertions.assertThat(result).isEqualTo(1);
//    }

    @Test
    public void 모든게시글가져오기(BoardDto boardDto) {

        List<FreeBoard> boards = freeBoardService.boards(boardDto);
        Assertions.assertThat(boards.size()).isEqualTo(3);

    }

    @Test
    public void 게시글갯수가져오기(PageDto pageDto){
        int count = freeBoardService.selectBoardTotalCount(pageDto);
        Assertions.assertThat(count).isEqualTo(61447);

    }

   // @Test
//    public void 게시글수정하기() {
//        FreeBoard freeBoard = new FreeBoard();
//
//        //게시글 하나 가져오기
//        freeBoard = freeBoardService.board(1);
//        freeBoard.setContent("수정2번째");
//
//        int result = freeBoardService.updateBaord(freeBoard);
//
//        FreeBoard freeBoard2 = freeBoardService.board(1);
//        Assertions.assertThat(result).isEqualTo(1);
//        Assertions.assertThat(freeBoard2.getContent()).isEqualTo("수정2번째");
//
//
//    }

    @Test
    public void 게시글삭제하기(){

    }

}