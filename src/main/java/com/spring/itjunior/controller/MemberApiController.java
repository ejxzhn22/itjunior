package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Member;
import com.spring.itjunior.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    /****** 회원 ID 찾기 *******/
    @PostMapping("/auth/member/find-id")
    public ResponseEntity<String> findIdProc(@RequestBody Member member) {
        String resultSuccessOne = memberService.findNameAndEmail(member);
        if (resultSuccessOne.equals("fail")) {
            return new ResponseEntity<>("fail", HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultSuccessOne, HttpStatus.OK);
    }

    /****** 회원 PASSWORD 찾기 *******/
    @PostMapping("/auth/member/find-pw")
    public ResponseEntity<Integer> findPwProc(@RequestBody Member member) {
        log.info("UserId, Name 넘겨받은 Member >>> {}",member);
        int resultIdx = memberService.findUserIdAndName(member);
        if (resultIdx == 0) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(resultIdx, HttpStatus.OK);
    }

    /****** 회원 Password 찾기 -> 새 비밀번호 변겅 *******/
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

    /****** 회원가입 ID 체크 로직 *******/
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
    /****** 회원가입 및 수정 Email 체크 로직 *******/
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
    /****** 회원가입 및 수정 NickName 체크 로직 *******/
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


    /****** 마이페이지 - 회원수정 전 Password 매치 로직 *******/
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

    /****** 회원 탈퇴 *******/
    @DeleteMapping("/member/{idx}")
    public String deleteMember(@PathVariable("idx") int member_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        try{
            //일반회원, oauth회원 삭제 분기
            if (principalDetails.getMember().getProvider() == null) {
                log.info("일반회원 삭제 요청..!");
                Member deleteMember = memberService.findByIdx(member_idx);
                boolean resultDelete = memberService.deleteByIdx(deleteMember);
                return resultDelete==true ? "success" : "fail";
            }else {
                boolean resultDelete = memberService.deleteMemberByIdxForOauth(member_idx);
                SecurityContextHolder.clearContext();
                return resultDelete==true ? "success" : "fail";
            }
        }catch (EmptyResultDataAccessException e){
            e.printStackTrace();
        }
        return "error";
    }

    /****** 회원가입 및 수정 Email부분 select option값 매치 로직 *******/
    @GetMapping("/auth/email/direct/{emailVal}")
    public Map<String, String> directSelectEmail(@PathVariable String emailVal) {
        String[] emailArray = getEmailArray(emailVal);
        String duplicationCheck = "";
        if (emailArray.length == 2) {
            log.info("split email >>> {},{}",emailArray[0],emailArray[1]);
            duplicationCheck = memberService.emailCheckByEmail(emailVal);
        } else if(emailArray.length == 3){
            log.info("split email >>> {},{},{}",emailArray[0],emailArray[1],emailArray[2]);
            duplicationCheck = memberService.emailCheckByEmail(emailArray[0]+emailArray[2]);
        }
        Map<String, String> resultSet = new HashMap<>();
        resultSet.put("result", duplicationCheck);
        resultSet.put("email", emailArray[0]);
        return resultSet;
    }
    private String[] getEmailArray(String emailProc) {
        String[] emailArray = emailProc.split("@");
        return emailArray;
    }

}
