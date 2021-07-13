package com.spring.itjunior.controller;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import javax.servlet.http.HttpSession;

/**
 * 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth 허용
 * 그냥 주소가 / 이면 home.jsp 허용
 * static 이하에 있는 /js/**, /css/**, /js/** 허용
 * 쉽게말해 Mapping에 인증이 필요없는 것들은 /auth를 붙일것.
 */
@Log4j2
@Controller
public class MemberController {

    private MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }

    @PostMapping("/auth/join")
    public String join(Member member) {
        System.out.println(member.toString());
        boolean resultJoin = memberService.saveMemberInfo(member);
        if (resultJoin == false) {
            return "error404";
        }
        return "redirect:/";
    }

    @GetMapping("/auth/loginForm")
    public String loginForm(Model model) {
        return "member/loginForm";
    }

    @GetMapping("/auth/member/{idx}")
    public String detailIdx(@PathVariable("idx") int member_idx, Model model) {
        Member resultMemberInfo = memberService.findByIdx(member_idx);
        model.addAttribute("member", resultMemberInfo);
        return "member/info";
    }

    @GetMapping("/auth/updateForm")
    public String updateForm(Model model) {
        Member member = memberService.findByIdx(1); //테스트
        model.addAttribute("member", member);
        return "member/updateForm";
    }

    @PutMapping("/auth/member/{idx}")
    public String updateMember(@PathVariable("idx") int member_idx, Member requestMember) {
        log.info("password >>> {}",requestMember.getPassword());
        memberService.updateMemberInfo(member_idx,requestMember);
        return "redirect:/";
    }


//    전통적인 로그인 방식 (security 사용 아닌 것)
//    @PostMapping("/auth/login")
//    public String login(Member params, HttpSession session) {
//        log.info("MemberController : login 호출됨.");
//
//        Member loginMember = memberService.loginMember(params);
//
//        log.info("loginMember>>>>{}",loginMember);
//        if (loginMember != null) {
//            memberService.updateLastLogin(loginMember);
//            session.setAttribute("info",loginMember);
//        }
//        return "redirect:/";
//    }
}
