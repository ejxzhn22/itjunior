package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.dto.JoinDto;
import com.spring.itjunior.dto.UpdateMemberDto;
import com.spring.itjunior.service.MailService;
import com.spring.itjunior.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * 인증이 안된 사용자들이 출입할 수 있는 경로를 /auth 허용
 * 그냥 주소가 / 이면 home.jsp 허용
 * static 이하에 있는 /js/**, /css/**, /js/** 허용
 * 쉽게말해 Mapping에 인증이 필요없는 것들은 /auth를 붙일것.
 */
@Log4j2
@RequiredArgsConstructor
@Controller
public class MemberController {

    private final MemberService memberService;
    private final AuthenticationManager authenticationManager;
    private final MailService mailService;


    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }
    @PostMapping("/auth/join")
    public String join(@Valid JoinDto joinDto, BindingResult bindingResult,Model model) {
        boolean resultJoin = memberService.saveMemberInfo(joinDto);
        return "redirect:/";
    }

    @GetMapping("/auth/member/find-id")
    public String findIdForm() {
        return "member/findIdForm";
    }

    @GetMapping("/auth/member/find-password")
    public String findPwdForm() {
        return "member/findPwdForm";
    }

    @GetMapping("/auth/member/find-password-certification/{idx}")
    public String findPwdByEmail(@PathVariable("idx") int member_idx, Model model) {
        log.info("memberIdx >>> {}",member_idx);

        Member member= memberService.findByIdx(member_idx);
        String encEmail = memberService.partialEncEmail(member.getEmail());
        int certificationNum = mailService.mailSend(member.getEmail(),member.getName());
        log.info("email전송 서비스 완료,, 인증번호 >>> {}",certificationNum);
        model.addAttribute("email", encEmail);
        model.addAttribute("certificationNum", certificationNum);
        model.addAttribute("member", member);

        return "member/findPwdCertificationForm";
    }

    @GetMapping("/auth/member/change-pw/{idx}")
    public String changePwd(@PathVariable("idx") int member_idx, Model model) {
        log.info("비밀번호 변경 페이지 진입,, member_idx >>> {}",member_idx);
        model.addAttribute("member_idx",member_idx);
        return "member/changePasswordForm";
    }



    @GetMapping("/auth/loginForm")
    public String loginForm(Model model) {
        return "member/loginForm";
    }


    @GetMapping("/member/{idx}")
    public String detailIdx(@PathVariable("idx") int member_idx, Model model) {
        Member resultMemberInfo = memberService.findByIdx(member_idx);
        model.addAttribute("member", resultMemberInfo);
        return "member/info";
    }


    //비밀번호 체크 페이지 >>>완료 후 회원수정 페이지.
    @GetMapping("/mypage/passwordCheck")
    public String passwordCheckForm() {
        return "mypage/passwordCheckForm";
    }
    @PostMapping("/member/updateForm")
    public String updateForm(@ModelAttribute("member") Member member) {
        log.info("uadateForm에서 받은 password >>> {}",member.toString());
        return "member/updateForm";
    }
    @PutMapping("/member")
    public String updateMember(@RequestParam("originPwd") String originPwd,@Valid UpdateMemberDto updateMemberDto, BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("들어온 member >> {}",updateMemberDto.toString());
        log.info("수정 요청한 password >>> {}",updateMemberDto.getPassword());
        log.info("평문 비밀번호 >>> {}",originPwd);

        String updatedPwd = updateMemberDto.getPassword();
        boolean resultUpdate = memberService.updateMemberInfo(updateMemberDto);

        if (StringUtils.isBlank(updatedPwd)) { //받아온 비밀번호가 null,"" 일때
            log.info("비밀번호를 수정하지 않았습니다. 평문 비밀번호를 가져오세요.");
            forceLoginProc(updateMemberDto,originPwd);
        }else {
            forceLoginProc(updateMemberDto,updatedPwd);
        }

        log.info("수정된 세션 비밀번호 >>> {}",principalDetails.getPassword());

        return "redirect:/";
    }


    //강제 로그인 처리(수정시 세션 업데이트 로직),,
    //update form에서 패스워드가 수정되는 값이 null이거나 ""공백 일때 기존회원 평문 비밀번호(패스워드 수정 안했을때)
    //update form에서 패스워드를 수정 하였다면 수정한 평문 비밀번호(패스워드 수정 했을때)
    private void forceLoginProc(UpdateMemberDto updateMemberDto,String sessionPwd) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(updateMemberDto.getUserId(),sessionPwd));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }


    @DeleteMapping("/member/{idx}")
    public String deleteMember(@PathVariable("idx") int member_idx) {
        try{
            boolean resultDelete = memberService.deleteByIdx(member_idx);
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
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
