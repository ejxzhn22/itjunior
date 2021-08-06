package com.spring.itjunior.controller;

import com.spring.itjunior.dto.PageDto;
import com.spring.itjunior.service.NaverNewsService;
import com.spring.itjunior.service.PagingService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class NewsApiController {

    private final NaverNewsService naverNewsService;
    private final PagingService pagingService;

    @GetMapping("/news")
    public String news(Model model, PageDto pageDto){


        JSONObject jsonObject = naverNewsService.newsApiList(pageDto);
        System.out.println("total"+jsonObject.get("total").toString());
        pageDto = pagingService.makeNewsPaging(pageDto,jsonObject);

        model.addAttribute("page", pageDto);
        model.addAttribute("result", jsonObject.get("items"));

        return "news/newsApiList";
    }

}
