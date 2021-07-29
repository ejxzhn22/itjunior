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

public class JobApiController {


    @GetMapping("/jobs")
    public String job(){

        return "job/jobList";
    }
}
