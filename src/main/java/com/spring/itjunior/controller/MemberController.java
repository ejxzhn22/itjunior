package com.spring.itjunior.controller;

import com.spring.itjunior.domain.MemberDTO;
import com.spring.itjunior.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Log4j2
@Controller
//@RequestMapping("/member/*")
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        model.addAttribute("params", new MemberDTO());
        return "member/loginForm";
    }

    @PostMapping("/login")
    public String login(MemberDTO params, HttpSession session) {
        log.info("MemberController : login 호출됨.");

        MemberDTO loginMember = memberService.loginMember(params);

        log.info("loginMember>>>>{}",loginMember);
        if (loginMember != null) {
            memberService.updateLastLogin(loginMember);
            session.setAttribute("info",loginMember);
        }
        return "redirect:/";
    }
}
