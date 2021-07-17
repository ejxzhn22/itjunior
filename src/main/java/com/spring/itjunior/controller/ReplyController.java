package com.spring.itjunior.controller;

import com.spring.itjunior.domain.Reply;
import com.spring.itjunior.service.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReplyController {

    private final ReplyService replyService;

    @ResponseBody
    @GetMapping("/replies/{free_idx}")
    public List<Reply> replies(@PathVariable int free_idx) {

        return replyService.replies(free_idx);
    }
}
