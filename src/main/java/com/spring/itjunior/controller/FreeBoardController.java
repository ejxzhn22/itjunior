package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.*;
import com.spring.itjunior.dto.BoardDto;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.paging.Criteria;
import com.spring.itjunior.paging.PaginationInfo;
import com.spring.itjunior.service.FreeBoardService;
import com.spring.itjunior.service.MemberService;
import com.spring.itjunior.service.ReplyService;
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
    private  final ReplyService replyService;

    //자유게시판 이동

    @RequestMapping("/boards")
    public String boards(Model model ,PageDto pageDto) {

        // 글 카테고리 가져오기
        List<Category> categories = freeBoardService.category();
        System.out.println("key: "+pageDto.getSearchKeyword());

        int boardTotalCount = freeBoardService.selectBoardTotalCount(pageDto);
        System.out.println("count : " +boardTotalCount);

        PaginationInfo paginationInfo = new PaginationInfo(pageDto);
        paginationInfo.setTotalRecordCount(boardTotalCount);

        System.out.println("page"+paginationInfo);

        pageDto.setPaginationInfo(paginationInfo);

        System.out.println("pageDto"+pageDto);
        List<FreeBoard> boards = freeBoardService.boards(pageDto);

        model.addAttribute("boards", boards);
        model.addAttribute("page", pageDto);

        return "freeBoard/freeBoardList";
    }

    //글 상세 페이지 이동
    @GetMapping("/boards/{free_idx}")
    public String boardDetail(@PathVariable int free_idx, Model model,@AuthenticationPrincipal PrincipalDetails principalDetails){

        // 조회수 올리기
        freeBoardService.viewUpdate(free_idx);

        //추천수 가져오기
        int likecnt = freeBoardService.viewcnt(free_idx);

        //추천상태
        FreeLike freeLike = new FreeLike();
        freeLike.setFree_idx(free_idx);
        freeLike.setMember_idx(principalDetails.getMember().getMember_idx());
        boolean likeState = freeBoardService.likeState(freeLike);

        // 댓글 갯수
        int replycnt = replyService.replycnt(free_idx);

        //게시글에서 좋아요누른 댓글 가져오기


        //DTO에 담아서 보낸다.
        BoardDto boardDto = freeBoardService.selectBoard(free_idx);
        boardDto.setLikecnt(likecnt);
        String title = boardDto.getTitle().replace("["+boardDto.getCate_name()+"]", "");
        boardDto.setTitle(title);
        boardDto.setLikeState(likeState);
        boardDto.setReplycnt(replycnt);

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

        FreeBoard freeBoard = new FreeBoard();
        freeBoard.setMember_idx(principalDetails.getMember().getMember_idx());
        freeBoard.setWriter(principalDetails.getMember().getNickname());
        freeBoard.setCategory(boardDto.getCategory());
        freeBoard.setTitle(boardDto.getCate_name()+" "+boardDto.getTitle());
        freeBoard.setContent(boardDto.getContent());
        freeBoard.setDelete_yn(DeleteYN.N);

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
        FreeLike freeLike = new FreeLike();
        freeLike.setFree_idx(free_idx);
        freeLike.setMember_idx(principalDetails.getMember().getMember_idx());

        return freeBoardService.freeLike(freeLike);
    }

    //글 추천 취소하기
    @ResponseBody
    @DeleteMapping("/boards/{free_idx}/likes")
    public int DeleteFreeLike(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails){
        System.out.println("취소하기");
        FreeLike freeLike = new FreeLike();
        freeLike.setFree_idx(free_idx);
        freeLike.setMember_idx(principalDetails.getMember().getMember_idx());
        System.out.println("free_idx: " + free_idx);
        System.out.println("member_idx: " + principalDetails.getMember().getMember_idx());
        return freeBoardService.deleteFreeLike(freeLike);
    }

    // 글 수정 이동
    @GetMapping("/boards/{free_idx}/update")
    public String freeBoardUpdateForm(@PathVariable int free_idx, Model model) {
        BoardDto boardDto = freeBoardService.selectBoard(free_idx);
        String title = boardDto.getTitle().replace("["+boardDto.getCate_name()+"]", "");
        boardDto.setTitle(title);
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
