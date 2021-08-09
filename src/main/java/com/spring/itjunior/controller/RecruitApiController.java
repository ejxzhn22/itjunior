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

import java.util.HashMap;
import java.util.Map;

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
    @GetMapping("/isLike/{job_idx}")
    public Map<String,Object> isRecruitLikeTF(@PathVariable long job_idx, @RequestBody RecruitLike recruitLike) {
        boolean islikeTF = recruitService.isRecruitLike(recruitLike);
        int count = recruitService.selectLikeCount(job_idx);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isLike",islikeTF);
        resultMap.put("likeCnt", count);

        return resultMap;
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

    //좋아요 취소 했을 때
    @DeleteMapping("/like/{job_idx}")
    public ResponseEntity<String> recruitLikeCancel(@PathVariable long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        boolean cancelTF = recruitService.cancelRecruitLike(job_idx, principalDetails);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
