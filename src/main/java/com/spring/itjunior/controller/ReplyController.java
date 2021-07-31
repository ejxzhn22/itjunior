package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Reply;
import com.spring.itjunior.domain.ReplyLike;
import com.spring.itjunior.dto.ReplyDto;
import com.spring.itjunior.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    // 댓글 가져오기
    @ResponseBody
    @GetMapping("/replies/{free_idx}")
    public List<Reply> replies(@PathVariable int free_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return replyService.replies(free_idx, principalDetails);
    }

    //부모댓글 추가
    @ResponseBody
    @PostMapping("/replies/{free_idx}/insertParent")
    public Reply insertParent(@PathVariable int free_idx, @RequestBody ReplyDto replyDto){

        int reply_id = replyService.insertParent(free_idx, replyDto);

        return replyService.reply(reply_id);
    }

    //대댓글 추가
    @ResponseBody
    @PostMapping("/replies/{free_idx}/insertChild")
    public Reply insertChild(@PathVariable int free_idx, @RequestBody ReplyDto replyDto){

        return replyService.insertChild(free_idx, replyDto);
    }


    //부모 댓글 삭제
    @ResponseBody
    @GetMapping("replies/{reply_idx}/deleteParent")
    public int deleteParent(@PathVariable int reply_idx) {
       return replyService.deleteParent(reply_idx);
    }

    //대댓글 삭제
    @ResponseBody
    @GetMapping("replies/{reply_idx}/deleteChild")
    public int deleteChild(@PathVariable int reply_idx) {
        return replyService.deleteChild(reply_idx);
    }

    //댓글 좋아요
    @ResponseBody
    @PostMapping("/replies/{reply_idx}/likes")
    public int likeParent(@PathVariable int reply_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return replyService.likeParent(reply_idx,principalDetails);
    }

    //댓글 좋아요 취소
    @ResponseBody
    @DeleteMapping("/replies/{reply_idx}/likes")
    public int likeParentDelete(@PathVariable int reply_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return replyService.likeParentDelete(reply_idx,principalDetails);
    }


    //댓글 싫어요
    @ResponseBody
    @PostMapping("/replies/{reply_idx}/unlikes")
    public int unlikeParent(@PathVariable int reply_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return replyService.unlikeParent(reply_idx,principalDetails);
    }

    //댓글 좋아요 취소
    @ResponseBody
    @DeleteMapping("/replies/{reply_idx}/unlikes")
    public int unlikeParentDelete(@PathVariable int reply_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {

        return replyService.unlikeParentDelete(reply_idx,principalDetails);
    }
}
