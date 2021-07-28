package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.dto.QnaDto;
import com.spring.itjunior.paging.PaginationInfo;
import com.spring.itjunior.service.FreeBoardService;
import com.spring.itjunior.service.MemberService;
import com.spring.itjunior.service.QnaBoardService;
import com.spring.itjunior.service.ReplyService;
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

    //큐앤에이 목록
    @RequestMapping("/qnaboards")
    public String boards(Model model, PageDto pageDto) {

        int qnaTotalCount = qnaBoardService.selectBoardTotalCount(pageDto);
        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(qnaTotalCount);
        pageDto.setPaginationInfo(paginationInfo);

        System.out.println("page"+pageDto);
        System.out.println("pageinfo"+paginationInfo);

        List<QnaBoard> list = qnaBoardService.qnaBoards(pageDto);
        model.addAttribute("qnas", list);
        model.addAttribute("page", pageDto);

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
        QnaDto dto = new QnaDto();

        QnaBoard qnaBoard = qnaBoardService.qnaBoard(qna_idx);
        dto.setTitle(qnaBoard.getTitle());
        dto.setContent(qnaBoard.getContent());
        dto.setWriter(qnaBoard.getWriter());
        if(qnaBoard.getSecret_yn() == DeleteYN.Y){
            dto.setSecret_yn(true);
        }else {
            dto.setSecret_yn(false);
        }
        dto.setQna_idx(qna_idx);
        dto.setCreate_time(qnaBoard.getCreate_time());
        dto.setReply_yn(qnaBoard.getReply_yn());


        if(qnaBoard.getReply_yn().equals(DeleteYN.Y)){
            Answer answer = qnaBoardService.answer(qna_idx);
            System.out.println("answer : " +answer);
            dto.setAnswer_title(answer.getTitle());
            dto.setAnswer_content(answer.getContent());
            dto.setAnswer_writer(answer.getWriter());
            dto.setAnswer_create_time(answer.getCreate_time());
        }
        System.out.println("dto : " + dto);

        model.addAttribute("board",dto);

        return "qnaBoard/qnaBoardDetail";
    }

    //큐앤에이 글쓰기 완료
    @PostMapping("/qnaboards/form")
    public String newQnaboard(QnaDto qnaDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        System.out.println("qnadto : " + qnaDto);

        QnaBoard qnaBoard = new QnaBoard();
        qnaBoard.setTitle(qnaDto.getTitle());
        qnaBoard.setContent(qnaDto.getContent());
        qnaBoard.setWriter(principalDetails.getMember().getNickname());
        qnaBoard.setDelete_yn(DeleteYN.N);
        qnaBoard.setReply_yn(DeleteYN.N);
        if(qnaDto.isSecret_yn()){
            qnaBoard.setSecret_yn(DeleteYN.Y);
        }else{
            qnaBoard.setSecret_yn(DeleteYN.N);
        }
        qnaBoard.setSecret_pwd(qnaDto.getSecret_pwd());
        qnaBoard.setMember_idx(principalDetails.getMember().getMember_idx());

        qnaBoardService.newQna(qnaBoard);

        return "redirect:/qnaboards";
    }

    // 상세페이지 가기전 비번 체크
    @ResponseBody
    @PostMapping("/qnaboards/pwdCheck/{qna_idx}")
    public boolean checkPwd(@PathVariable int qna_idx, @RequestBody Map<String,Object> pwd){

       QnaBoard qnaBoard = qnaBoardService.qnaBoard(qna_idx);
        System.out.println("입력 비번 : " + pwd.get("pwd"));
        System.out.println("원래 비번 : " + qnaBoard.getSecret_pwd());
       if(qnaBoard.getSecret_pwd().equals(pwd.get("pwd"))){
           return true;
       }else{
        return false;
       }

    }

    //답변 등록
    @PostMapping("/qnaboards/{qna_idx}/answer")
    public String answer(@PathVariable int qna_idx, QnaDto qnaDto){
        Answer answer = new Answer();
        answer.setTitle(qnaDto.getAnswer_title());
        answer.setContent(qnaDto.getAnswer_content());
        answer.setQna_idx(qna_idx);

        int result = qnaBoardService.newAnswer(answer);
        int result2 = qnaBoardService.updateQna(qna_idx);
        System.out.println(result+"  " +result2);

        return "redirect:/qnaboards";

    }
}
