package com.spring.itjunior.controller;

import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class FreeBoardController {

    private final FreeBoardService freeBoardService;

    //자유게시판 이동
    @GetMapping("/boards")
    public String boards(Model model) {
        List<FreeBoard> boards = freeBoardService.boards();
        model.addAttribute("boards", boards);

        return "freeBoard/freeBoard";
    }

    //글 상세 페이지 이동
    @GetMapping("/boards/{free_idx}")
    public String boardDetail(@PathVariable int free_idx, Model model){

        // 조회수 올리기
        freeBoardService.viewUpdate(free_idx);

        FreeBoard freeBoard = freeBoardService.board(free_idx);

        //추천수

        model.addAttribute("board",freeBoard);

        return "freeBoard/freeBoardDetail";

    }

    //글쓰기 이동
    @GetMapping("/boards/new")
    public String newBoard(Model model){
        // 글 카테고리 가져오기
        List<Category> categories = freeBoardService.category();
        model.addAttribute("categories", categories);

        return "freeBoard/freeBoardForm";
    }

    //글쓰기
    @PostMapping("/boards/new")
    public String newBoard(BoardDto boardDto) {

        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setCategory(boardDto.getCategory());
        freeBoard.setTitle(boardDto.getTitle());
        freeBoard.setContent(boardDto.getContent());

        //freeBoardService.newBoard(freeBoard);

        return "redirect:/boards";
    }

    //글 추천하기
    @ResponseBody
    @PostMapping("/boards/{free_idx}/likes")
    public int freeLike(@PathVariable int free_idx){

        return freeBoardService.freeLike(free_idx);
    }

    //글 추천 취소하기
    @ResponseBody
    @DeleteMapping("/boards/{free_idx}/likes")
    public int DeleteFreeLike(@PathVariable int free_idx){

        return freeBoardService.freeLike(free_idx);
    }
}
