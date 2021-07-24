package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.service.FreeBoardService;
import com.spring.itjunior.service.MemberService;
import com.spring.itjunior.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class qnaBoardController {

    //이름 변경 부탁드립니다.
    @GetMapping("/qnaboards")
    public String boards(Model model) {

        return "qnaBoard/qnaBoardList";
    }
    //자유게시판 이동
    @GetMapping("/qnaboards/form")
    public String boardsform(Model model) {

        return "qnaBoard/qnaBoardForm";
    }
}
