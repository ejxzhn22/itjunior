package com.spring.itjunior.controller;

import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        FreeBoard freeBoard = freeBoardService.board(free_idx);
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


}
