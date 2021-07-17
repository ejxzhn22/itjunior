package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
public class MemberApiController {

    private MemberService memberService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public MemberApiController(MemberService memberService,BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.memberService = memberService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @PostMapping("/auth/member/find-id")
    public String findIdProc(@RequestBody Member member) {
        String resultSuccessOne = memberService.findNameAndEmail(member);
        if (resultSuccessOne.equals("fail")) {
            return "fail";
        }
        return resultSuccessOne;
    }



    @PostMapping("/mypage/passwordCheck")
    public String passwordCheck(@RequestBody Member member, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("*****패스워드 확인 컨트롤러 진입*****");
        log.info("평문 password >>> {}",member.getPassword());
        log.info("암호화 password >>> {}",principalDetails.getPassword());
        log.info(bCryptPasswordEncoder.matches(member.getPassword(), principalDetails.getPassword()));

        if (bCryptPasswordEncoder.matches(member.getPassword(), principalDetails.getPassword())) {
            log.info("password match success!!");
            return "checkSuccess";
        }
        return "fail";
    }
}
