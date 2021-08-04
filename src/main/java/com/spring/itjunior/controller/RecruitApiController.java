package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.domain.RecruitLike;
import com.spring.itjunior.service.RecruitService;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Log4j2
@RestController
@RequestMapping("/job/*")
public class RecruitApiController {

    private RecruitService recruitService;

    @Autowired
    public RecruitApiController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    //좋아요 여부
    @GetMapping("/isLike")
    public ResponseEntity<String> isRecruitLikeTF(@RequestBody RecruitLike recruitLike) {
        boolean islikeTF = recruitService.isRecruitLike(recruitLike);
        if (islikeTF) {
            return new ResponseEntity<String>("isTrue", HttpStatus.OK);
        }
        return new ResponseEntity<>("isFalse", HttpStatus.OK);
    }

    //좋아요 눌렀을 때
    @PostMapping("/like/{job_idx}")
    public ResponseEntity<String> recruitLike(@PathVariable long job_idx, @RequestBody RecruitDTO recruitDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (recruitService.getRecruitInfo(job_idx) == null) {
            recruitService.saveRecruitInfo(recruitDTO);
        }
        boolean likeTF = recruitService.pushRecruitLike(job_idx, principalDetails);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
