package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class HomeController {

    @Autowired
    private MemberService memberService;

//    @AuthenticationPrincipal PrincipalDetails principalDetails
    @GetMapping("/")
    public String home() {
        log.info("**********Welcome to ITJunoir HomeController**********");
//        log.info("로그인된 사용자 아이디 >>> {}", principalDetails.getUsername());
        return "home";
    }

}
