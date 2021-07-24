package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Log4j2
@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        log.info("**********Welcome to ITJunoir HomeController**********");
        return "home";
    }

    @GetMapping("/oauth/")
    public String notNamehome(@AuthenticationPrincipal PrincipalDetails principalDetails, Member member) {
        log.info("**********Welcome to ITJunoir HomeController**********");
        log.info("oauth로 로그인된 회원정보 >>> {}",principalDetails.getMember().toString());
        log.info("member로 넘어온 정보 >>> {}",member.toString());
        log.info("첫번째 로그인 인가요? >>> {}",principalDetails.isFirstOauthLogin());
        if (principalDetails.isFirstOauthLogin()) {
            log.info("oauth 아이디 최초 로그인 입니다. 회원수정 폼으로 이동하여 name(성명)과 nickname(활동이름)을 수정하여 주십시오.");

            String fixedPwd = "itjunior"+principalDetails.getMember().getUuid();
            log.info("고정 평문 비밀번호 >>> {}",fixedPwd);
            member.setPassword(fixedPwd);

            return "member/updateForm";
        }
        return "home";
    }

}
