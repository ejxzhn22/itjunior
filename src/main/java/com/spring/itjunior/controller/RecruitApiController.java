package com.spring.itjunior.controller;

import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.service.RecruitService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Log4j2
@Controller
@RequestMapping("/recruit/*")
public class RecruitApiController {

    private RecruitService recruitService;

    @Autowired
    public RecruitApiController(RecruitService recruitService) {
        this.recruitService = recruitService;
    }

    @GetMapping("/api/list")
    public String recruitApiList(Model model) {

        String resultInfo = recruitService.getApiList();
        model.addAttribute("recruit",resultInfo);

        return "job/jobList";
    }


    @PostMapping("/newInfo")
    public String registerRecruitInfo(@RequestBody RecruitDTO params) {

        log.info("close_date >>> {}", params.getClose_date());

        boolean isRegistered = recruitService.saveRecruitInfo(params);

        return (isRegistered == true) ? "채용정보 등록 성공" : "채용정보 등록 실패..";
    }
}
