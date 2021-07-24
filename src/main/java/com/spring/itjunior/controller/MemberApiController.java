package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @PostMapping("/auth/join/idCheck")
    public ResponseEntity<String> idCheck(@RequestBody Member member) {
        log.info("userId 중복 체크 Member >>> {}",member.getUserId());
        String resultCheck = memberService.idCheckByUserId(member.getUserId());
        if (resultCheck.equals("same")) {
            //아이디가 중복이면 "same"을 리턴.
            return new ResponseEntity<String>("same",HttpStatus.OK);
        }else if(resultCheck.equals("notSame")){
            //닉네임이 중복이 아니면 "notSame"을 리턴.
            return new ResponseEntity<String>("notSame", HttpStatus.OK);
        }
        log.info("아이디 체크 서버호출 에러....");
        return new ResponseEntity<String>("notSame",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/auth/join/emailCheck")
    public ResponseEntity<String> emailCheck(@RequestBody Member member) {
        log.info("email 중복 체크 Member >>> {}",member.toString());
        String resultCheck = memberService.emailCheckByEmail(member.getEmail());
        if (resultCheck.equals("same")) {
            //닉네임이 중복이면 "same"을 리턴.
            return new ResponseEntity<String>("same",HttpStatus.OK);
        }else if(resultCheck.equals("notSame")){
            //닉네임이 중복이 아니면 "notSame"을 리턴.
            return new ResponseEntity<String>("notSame", HttpStatus.OK);
        }
        log.info("이메일 체크 서버호출 에러....");
        return new ResponseEntity<String>("notSame",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @PostMapping("/auth/join/nickNameCheck")
    public ResponseEntity<String> nickNameCheck(@RequestBody Member member) {
        log.info("nickname 중복체크 Member >>> {}",member.toString());
        String resultCheck = memberService.nickNameCheckByNickname(member.getNickname());
        if (resultCheck.equals("same")) {
            //닉네임이 중복이면 "same"을 리턴.
            return new ResponseEntity<String>("same",HttpStatus.OK);
        }else if(resultCheck.equals("notSame")){
            //닉네임이 중복이 아니면 "notSame"을 리턴.
            return new ResponseEntity<String>("notSame", HttpStatus.OK);
        }
        log.info("닉네임 체크 서버호출 에러....");
        return new ResponseEntity<String>("notSame",HttpStatus.INTERNAL_SERVER_ERROR);
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
