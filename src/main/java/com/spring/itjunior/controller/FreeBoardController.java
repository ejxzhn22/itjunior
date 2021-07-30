package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.service.FreeBoardService;
import com.spring.itjunior.service.PagingService;
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
    private final PagingService pagingService;

    //자유게시판 이동

    @GetMapping("/boards")
    public String boards(Model model ,PageDto pageDto) {
        //페이징 & 검색
        PageDto setPageDto = pagingService.makeFreePaging(pageDto);

        // 게시글 리스트
        List<FreeBoard> boards = freeBoardService.boards(setPageDto);

        model.addAttribute("boards", boards);
        model.addAttribute("page", setPageDto);

        return "freeBoard/freeBoardList";
    }

    //글 상세 페이지 이동
    @GetMapping("/boards/{free_idx}")
    public String boardDetail(@ModelAttribute PageDto pageDto,@PathVariable int free_idx, Model model
                                            ,@AuthenticationPrincipal PrincipalDetails principalDetails){
        // 조회수 올리기
        freeBoardService.viewUpdate(free_idx);

        //추천상태
        FreeLike freeLike = FreeLike.builder(free_idx,principalDetails.getMember().getMember_idx())
                .build();

        // 댓글 추천 상태
        boolean likeState = freeBoardService.likeState(freeLike);

        //DTO에 담아서 보낸다.
        BoardDto boardDto = freeBoardService.selectBoard(free_idx);
        boardDto.setDtoTitle(boardDto.getTitle(),boardDto.getCate_name());
        boardDto.setLikeState(likeState);



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
    @ResponseBody
    @PostMapping("/boards/new")
    public String newBoard(BoardDto boardDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        FreeBoard freeBoard = FreeBoard.builder(principalDetails.getMember().getMember_idx()
                ,principalDetails.getMember().getNickname(), boardDto.getCate_name()+" "+boardDto.getTitle()
                ,boardDto.getContent(),DeleteYN.N,boardDto.getCategory())
                .build();

        int result = freeBoardService.newBoard(freeBoard);

        String resultmsg="";
        if(result>0){
            resultmsg = "<script>alert('글쓰기 성공'); location.href='/boards'</script>";
        }else{
            resultmsg = "<script>alert('글쓰기 실패'); location.href='/boards'</script>";
        }

        return resultmsg;
    }

    //글 추천하기
    @ResponseBody
    @PostMapping("/boards/{free_idx}/likes")
    public int freeLike(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){

        FreeLike freeLike = FreeLike.builder(free_idx,principalDetails.getMember().getMember_idx())
                .build();

        return freeBoardService.freeLike(freeLike);
    }

    //글 추천 취소하기
    @ResponseBody
    @DeleteMapping("/boards/{free_idx}/likes")
    public int DeleteFreeLike(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){

        FreeLike freeLike = FreeLike.builder(free_idx,principalDetails.getMember().getMember_idx())
                .build();

        return freeBoardService.deleteFreeLike(freeLike);
    }

    // 글 수정 이동
    @GetMapping("/boards/{free_idx}/update")
    public String freeBoardUpdateForm(@PathVariable int free_idx, Model model) {

        BoardDto boardDto = freeBoardService.selectBoard(free_idx);
        boardDto.setDtoTitle(boardDto.getTitle(),boardDto.getCate_name());

        model.addAttribute("board",boardDto);

        return "freeBoard/freeBoardUpdateForm";
    }

    //글수정
    @ResponseBody
    @PostMapping("/boards/{free_idx}/update")
    public String freeBoardUpdate(@PathVariable int free_idx, BoardDto boardDto){

        FreeBoard freeBoard = freeBoardService.board(free_idx);
        freeBoard.setTitle(boardDto.getCate_name()+" "+boardDto.getTitle());
        freeBoard.setContent(boardDto.getContent());

        int result = freeBoardService.updateBaord(freeBoard);

        String resultmsg="";
        if(result>0){
            resultmsg = "<script>alert('글을 수정했습니다!'); location.href='/boards/"+free_idx+"'</script>";
        }else{
            resultmsg = "<script>alert('수정 실패 ㅠ.ㅠ'); location.href='/boards/"+free_idx+"'</script>";
        }

        return resultmsg;
    }

    //글삭제
    @ResponseBody
    @GetMapping("/boards/{free_idx}/delete")
    public String freeBoardDelete(@PathVariable int free_idx){
        int result = freeBoardService.deleteBoard(free_idx);

        String resultmsg="";

        if(result>0){
            resultmsg = "<script>alert('글을 삭제했습니다!'); location.href='/boards'</script>";
        }else{
            resultmsg = "<script>alert('삭제 실패 ㅠ.ㅠ'); location.href='/boards'</script>";
        }
        return resultmsg;
    }
}
