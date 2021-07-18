package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.service.MemberService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<String> findIdProc(@RequestBody Member member) {
        String resultSuccessOne = memberService.findNameAndEmail(member);
        if (resultSuccessOne.equals("fail")) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultSuccessOne, HttpStatus.OK);
    }

    @PostMapping("/auth/member/find-pw")
    public ResponseEntity<Integer> findPwProc(@RequestBody Member member) {
        log.info("UserId, Name 넘겨받은 Member >>> {}",member);
        int resultIdx = memberService.findUserIdAndName(member);
        if (resultIdx == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultIdx, HttpStatus.OK);
    }

    @PutMapping("/auth/member/change-pw")
    public ResponseEntity<String> changePwProc(@RequestBody Member member) {
        log.info("비밀번호 변경 폼 Member >>> {}",member.toString());
        boolean resultUpdate = memberService.updateMemberPassword(member);
        if (!resultUpdate) {
            return new ResponseEntity<String>("failUpdate",HttpStatus.BAD_REQUEST);
        }
        log.info("비밀번호 변경 PUT Controller >>> 수정 완료!");
        return new ResponseEntity<String>("successUpdate",HttpStatus.OK);
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
