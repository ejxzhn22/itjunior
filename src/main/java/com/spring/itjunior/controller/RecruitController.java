package com.spring.itjunior.controller;

import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.service.RecruitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recruit/*")
public class RecruitController {

    private RecruitService recruitService;

    @Autowired
    public RecruitController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    @PostMapping("/newInfo")
    public String registerRecruitInfo(@RequestBody RecruitDTO params) {
        boolean isRegisterd = recruitService.saveRecruitInfo(params);

        return (isRegisterd == true) ? "채용정보 등록 성공" : "채용정보 등록 실패..";
    }
}
