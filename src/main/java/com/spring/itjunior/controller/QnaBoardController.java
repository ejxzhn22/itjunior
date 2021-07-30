package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.dto.QnaDto;
import com.spring.itjunior.paging.PaginationInfo;
import com.spring.itjunior.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Controller
public class QnaBoardController {

    private final QnaBoardService qnaBoardService;
    private final PagingService pagingService;

    //큐앤에이 목록
    @RequestMapping("/qnaboards")
    public String boards(Model model, PageDto pageDto) {
        //페이징 & 검색
        PageDto setPageDto = pagingService.makeQnaPaging(pageDto);

        List<QnaBoard> list = qnaBoardService.qnaBoards(setPageDto);

        model.addAttribute("qnas", list);
        model.addAttribute("page", setPageDto);

        return "qnaBoard/qnaBoardList";
    }

    //큐앤에이 글쓰기 이동
    @GetMapping("/qnaboards/form")
    public String boardsform(Model model) {

        return "qnaBoard/qnaBoardForm";
    }

    //큐앤에이 상세 이동
    @GetMapping("/qnaboards/{qna_idx}/detail")
    public String boardsdetail(@PathVariable int qna_idx, Model model) {
        QnaBoard qnaBoard = qnaBoardService.qnaBoard(qna_idx);

        QnaDto qnaDto = new QnaDto();
        QnaDto.setDto(qnaDto, qnaBoard.getTitle(), qnaBoard.getContent(), qnaBoard.getWriter(), qna_idx,
                qnaBoard.getCreate_time(), qnaBoard.getReply_yn(),qnaBoard.getSecret_yn());

        //답변이 있을 때
        if(qnaBoard.getReply_yn().equals(DeleteYN.Y)){
            Answer answer = qnaBoardService.answer(qna_idx);
            QnaDto.setAnswerDto(qnaDto, answer.getTitle(), answer.getContent()
                    , answer.getWriter(), answer.getCreate_time());
        }

        model.addAttribute("board",qnaDto);

        return "qnaBoard/qnaBoardDetail";
    }

    //큐앤에이 글쓰기 완료
    @PostMapping("/qnaboards/form")
    public String newQnaboard(QnaDto qnaDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        QnaBoard qnaBoard = new QnaBoard();
        QnaBoard.setQnaBoard(qnaBoard, qnaDto, principalDetails.getMember().getNickname()
                                            ,principalDetails.getMember().getMember_idx());

        qnaBoardService.newQna(qnaBoard);

        return "redirect:/qnaboards";
    }

    // 상세페이지 가기전 비번 체크
    @ResponseBody
    @PostMapping("/qnaboards/pwdCheck/{qna_idx}")
    public boolean checkPwd(@PathVariable int qna_idx, @RequestBody Map<String,Object> pwd){
       QnaBoard qnaBoard = qnaBoardService.qnaBoard(qna_idx);

        return qnaBoard.getSecret_pwd().equals(pwd.get("pwd"));
    }

    //답변 등록
    @PostMapping("/qnaboards/{qna_idx}/answer")
    public String answer(@PathVariable int qna_idx, QnaDto qnaDto){

        Answer answer = Answer.builder(qnaDto.getAnswer_title(),qnaDto.getAnswer_content(),qna_idx)
                .build();

        qnaBoardService.newAnswer(answer);
        qnaBoardService.updateQna(qna_idx);

        return "redirect:/qnaboards";

    }
}
