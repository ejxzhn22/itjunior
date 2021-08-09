package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.service.RecruitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/isScrap/{job_idx}")
    public Map<String,Object> isRecruitLikeTF(@PathVariable String job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        long job_idx_num = Long.parseLong(job_idx);
        boolean isScrapTF = recruitService.isRecruitScrap(job_idx_num, principalDetails);
        int count = recruitService.selectScrapCount(job_idx_num);

        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("isScrap",isScrapTF);
        resultMap.put("ScrapCnt", count);

        return resultMap;
    }

    //좋아요 눌렀을 때
    @PostMapping("/scrap/{job_idx}")
    public ResponseEntity<String> recruitScrap(@PathVariable long job_idx, @RequestBody RecruitDTO recruitDTO, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        if (recruitService.getRecruitInfo(job_idx) == null) {
            recruitService.saveRecruitInfo(recruitDTO);
        }
        boolean scrapTF = recruitService.pushRecruitScrap(job_idx, principalDetails);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }

    //좋아요 취소 했을 때
    @DeleteMapping("/scrap/{job_idx}")
    public ResponseEntity<String> recruitScrapCancel(@PathVariable long job_idx, @AuthenticationPrincipal PrincipalDetails principalDetails) {
        boolean cancelTF = recruitService.cancelRecruitScrap(job_idx, principalDetails);
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
}
