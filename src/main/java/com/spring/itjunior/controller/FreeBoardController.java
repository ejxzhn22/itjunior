package com.spring.itjunior.controller;

import com.spring.itjunior.domain.FreeBoard;
import com.spring.itjunior.service.FreeBoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

        return null;
    }


}
