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
    public List<Reply> replies(@PathVariable int free_idx) {

        return replyService.replies(free_idx);
    }

    //부모댓글 추가
    @ResponseBody
    @PostMapping("/replies/{free_idx}/insertParent")
    public Reply insertParent(@PathVariable int free_idx, @RequestBody ReplyDto replyDto){

        Reply reply = new Reply();
        reply.setFree_idx(free_idx);
        reply.setMember_idx(replyDto.getMember_idx());
        reply.setWriter(replyDto.getWriter());
        reply.setContent(replyDto.getContent());
        reply.setEmoji(replyDto.getEmoji());
        System.out.println("reply: " + reply);

        int reply_id = replyService.insertParent(reply);
        System.out.println("replyid : " + reply_id);

        return replyService.reply(reply_id);
    }

    //대댓글 추가
    @ResponseBody
    @PostMapping("/replies/{free_idx}/insertChild")
    public Reply insertChild(@PathVariable int free_idx, @RequestBody ReplyDto replyDto){

        Reply reply = new Reply();
        reply.setFree_idx(free_idx);
        reply.setMember_idx(replyDto.getMember_idx());
        reply.setWriter(replyDto.getWriter());
        reply.setContent(replyDto.getContent());
        reply.setEmoji(replyDto.getEmoji());
        reply.setParent_idx(replyDto.getParent_idx());
        reply.setReply_order(replyDto.getReply_order());
        System.out.println("reply: " + reply);

        return replyService.insertChild(reply);
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
        ReplyLike replyLike = new ReplyLike();
        replyLike.setReply_idx(reply_idx);
        replyLike.setMember_idx(principalDetails.getMember().getMember_idx());


        return replyService.likeParent(replyLike);
    }
}
