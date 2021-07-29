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
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    /****** 회원가입 *******/
    @GetMapping("/auth/joinForm")
    public String joinForm() {
        return "member/joinForm";
    }
    @PostMapping("/auth/join")
    public String join(@Valid JoinDto joinDto, BindingResult bindingResult,Model model, RedirectAttributes rttr) {
        boolean resultJoin = memberService.saveMemberInfo(joinDto);

        log.info("회원 등록 성공/실패 >>> {}",resultJoin);

        if (resultJoin) {
            rttr.addFlashAttribute("insertMsg","success");
            return "redirect:/";
        }
        return "redirect:/auth/joinForm";
    }

    /****** 회원 ID찾기 Form *******/
    @GetMapping("/auth/member/find-id")
    public String findIdForm() {
        return "member/findIdForm";
    }

    /****** 회원 PASSWORD 찾기 Form *******/
    @GetMapping("/auth/member/find-password")
    public String findPwdForm() {
        return "member/findPwdForm";
    }


    /****** 회원 PASSWORD 찾기 인증 Form (email 인증 Form) *******/
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

    /****** 회원 PASSWORD 찾기 - 이메일인증 - 새로운 비밀번호 생성 Form *******/
    @GetMapping("/auth/member/change-pw/{idx}")
    public String changePwd(@PathVariable("idx") int member_idx, Model model) {
        log.info("비밀번호 변경 페이지 진입,, member_idx >>> {}",member_idx);
        model.addAttribute("member_idx",member_idx);
        return "member/changePasswordForm";
    }


    /****** 로그인 Form *******/
    @GetMapping("/auth/loginForm")
    public String loginForm(Model model) {
        return "member/loginForm";
    }

    /****** 회원 상세보기 Form (아직 없음) *******/
    @GetMapping("/member/{idx}")
    public String detailIdx(@PathVariable("idx") int member_idx, Model model) {
        Member resultMemberInfo = memberService.findByIdx(member_idx);
        model.addAttribute("member", resultMemberInfo);
        return "member/info";
    }

    /****** 마이페이지 Form *******/
    @GetMapping("/mypage")
    public String mypageForm(Member member, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        member.setPassword("itjunior"+principalDetails.getMember().getUuid());
        log.info("mypage >>>{}",member.toString());
        return "mypage/mypage";
    }

    /****** 마이페이지 - 회원 수정하기 전 Password 체크 Form(일반회원 전용) *******/
    @GetMapping("/mypage/passwordCheck")
    public String updatePasswordCheckForm() {
        return "mypage/passwordCheckForm";
    }
    /****** 마이페이지 - 회원 탈퇴 전 Password 체크 Form(일반회원 전용) *******/
    @GetMapping("/mypage/deletePasswordCheck")
    public String deletePasswordCheckForm() {
        return "mypage/deletePasswordCheckForm";
    }

    /****** 마이페이지 - 회원 탈퇴 체크 - 회원 탈퇴사유 및 확정 Form *******/
    @GetMapping("/mypage/isDelete")
    public String oauthMemberDelete() {
        return "mypage/oauthMemberisDelete";
    }

    /****** 회원 수정 Form *******/
    @RequestMapping(value = "/member/updateForm",method = {RequestMethod.GET,RequestMethod.POST})
    public String updateForm(Member member,@AuthenticationPrincipal PrincipalDetails principalDetails) {
        log.info("passwordCheckForm 에서 받은 password >>> {}",member.toString());
        log.info("isFirstLogin >>> {}",principalDetails.isFirstOauthLogin());

        //oauth로그인 유저가 아닌 일반 유저면,,
        if (StringUtils.isBlank(principalDetails.getMember().getProvider())) {
            String fixedPwd = member.getPassword();
            log.info("고정 평문 비밀번호 >>> {}",fixedPwd);
            member.setPassword(fixedPwd);
            return "member/updateForm";
        }

        String fixedPwd = "itjunior"+principalDetails.getMember().getUuid();
        log.info("고정 평문 비밀번호 >>> {}",fixedPwd);
        member.setPassword(fixedPwd);

        return "member/updateForm";
    }

    /****** 회원 수정 *******/
    @PostMapping("/member")
    public String updateMember(@RequestParam("originPwd") String originPwd, @Valid UpdateMemberDto updateMemberDto,
                               BindingResult bindingResult, @AuthenticationPrincipal PrincipalDetails principalDetails, Model model, RedirectAttributes rttr) {
        log.info("들어온 member >> {}",updateMemberDto.toString());
        log.info("평문 비밀번호 >>> {}",originPwd);

        memberService.updateMemberInfo(updateMemberDto);

        String updatedPwd = updateMemberDto.getPassword();
        String userId = principalDetails.getUsername();
        if (StringUtils.isBlank(updatedPwd)) { //받아온 비밀번호가 null,"" 일때
            log.info("비밀번호를 수정하지 않았습니다. 평문 비밀번호를 가져오세요.");
            log.info("평문 >>> {}",originPwd);
            forceLoginProc(userId,originPwd);
        }else {
            forceLoginProc(userId,updatedPwd);
        }

        log.info("수정된 세션 비밀번호 >>> {}",principalDetails.getPassword());
        rttr.addFlashAttribute("udateMsg","success");
        return "redirect:/";
    }


    /****** 회원 수정 후 security session(context - Authentication)에 수정된 정보 등록 메서드 *******/
    //강제 로그인 처리(수정시 세션 업데이트 로직),,
    //update form에서 패스워드가 수정되는 값이 null이거나 ""공백 일때 기존회원 평문 비밀번호(패스워드 수정 안했을때)
    //update form에서 패스워드를 수정 하였다면 수정한 평문 비밀번호(패스워드 수정 했을때)
    private void forceLoginProc(String userId,String sessionPwd) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(userId,sessionPwd));
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
