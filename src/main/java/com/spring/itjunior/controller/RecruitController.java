package com.spring.itjunior.controller;

import com.spring.itjunior.config.auth.PrincipalDetails;
import com.spring.itjunior.domain.Category;
import com.spring.itjunior.domain.RecruitDTO;
import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.service.PagingService;
import com.spring.itjunior.service.RecruitService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Log4j2
@Controller
@AllArgsConstructor
@RequestMapping("/job/*")
public class RecruitController {

    private RecruitService recruitService;
    private PagingService pagingService;

    @GetMapping("/list")
    public String recruitApiList(Model model, PageDto pageDto) {
        JSONObject resultInfo = recruitService.getApiList(pageDto);

        PageDto setPageDto = pagingService.makeRecruitPaging(pageDto, resultInfo);

        //키워드 가져오기
        List<Category> categories = recruitService.keywordList();

        model.addAttribute("categories", categories);
        model.addAttribute("result",resultInfo);
        model.addAttribute("page",setPageDto);

        return "job/jobList";
    }

    @GetMapping("/mypage/scrapList")
    public String scrappedList(Model model, PageDto pageDto, @AuthenticationPrincipal PrincipalDetails principalDetails) {


//        model.addAttribute("categories", categories);
//        model.addAttribute("result",resultInfo);
//        model.addAttribute("page",setPageDto);
//        git test token
        return "job/jobList";
    }

}
