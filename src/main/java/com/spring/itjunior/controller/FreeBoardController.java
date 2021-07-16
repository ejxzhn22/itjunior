package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.service.FreeBoardService;
import com.spring.itjunior.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

        return "freeBoard/freeBoardList";
    }

    //글 상세 페이지 이동
    @GetMapping("/boards/{free_idx}")
    public String boardDetail(@PathVariable int free_idx, Model model){

        // 조회수 올리기
        freeBoardService.viewUpdate(free_idx);

        //추천수 가져오기
        int likecnt = freeBoardService.viewcnt(free_idx);

        //DTO에 담아서 보낸다.
        FreeBoard freeBoard = freeBoardService.board(free_idx);
        BoardDto boardDto = new BoardDto();
        boardDto.setFree_idx(free_idx);
        boardDto.setTitle(freeBoard.getTitle());
        boardDto.setContent(freeBoard.getContent());
        boardDto.setWriter(freeBoard.getWriter());
        boardDto.setCategory(freeBoard.getCategory());
        boardDto.setViewcnt(freeBoard.getViewcnt());
        boardDto.setLikecnt(likecnt);

        model.addAttribute("board",boardDto);

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
    public String newBoard(BoardDto boardDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setMember_idx(principalDetails.getMember().getMember_idx());
        freeBoard.setWriter(principalDetails.getMember().getNickname());
        freeBoard.setCategory(boardDto.getCategory());
        freeBoard.setTitle(boardDto.getTitle());
        freeBoard.setContent(boardDto.getContent());
        freeBoard.setDelete_yn(DeleteYN.N);

        freeBoardService.newBoard(freeBoard);

        return "redirect:/boards";
    }

    //글 추천하기
    @ResponseBody
    @PostMapping("/boards/{free_idx}/likes")
    public int freeLike(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){
        FreeLike freeLike = new FreeLike();
        freeLike.setFree_idx(free_idx);
        freeLike.setMember_idx(principalDetails.getMember().getMember_idx());

        return freeBoardService.freeLike(freeLike);
    }

    //글 추천 취소하기
    @ResponseBody
    @DeleteMapping("/boards/{free_idx}/likes")
    public int DeleteFreeLike(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){
        FreeLike freeLike = new FreeLike();
        freeLike.setFree_idx(free_idx);
        freeLike.setMember_idx(principalDetails.getMember().getMember_idx());

        return freeBoardService.freeLike(freeLike);
    }

    // 글 수정 이동
    @GetMapping("/boards/{free_idx}/update")
    public String freeBoardUpdateForm(@PathVariable int free_idx, Model model) {
        BoardDto boardDto = freeBoardService.selectBoard(free_idx);

        model.addAttribute("board",boardDto);


        return "freeBoard/freeBoardUpdateForm";
    }

    //글수정
    @PostMapping("/boards/{free_idx}/update")
    public String freeBoardUpdate(@PathVariable int free_idx, BoardDto boardDto){
        FreeBoard freeBoard = freeBoardService.board(free_idx);
        freeBoard.setTitle(boardDto.getTitle());
        freeBoard.setContent(boardDto.getContent());
        freeBoardService.updateBaord(freeBoard);

        return "redirect:/boards";
    }

    //글삭제
    @GetMapping("/boards/{free_idx}/delete")
    public String freeBoardDelete(@PathVariable int free_idx){
        freeBoardService.deleteBoard(free_idx);

        return "redirect:/boards";
    }
}
