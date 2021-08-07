package com.spring.itjunior.service;

import com.spring.itjunior.dto.PageDto;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Log4j2
@Service
public class ApiService {

    //사람인 요청 Url
    private final String requestUrl = "https://oapi.saramin.co.kr/job-search";
    //사람인 요청 key
    private final String serviceKey = "9YSJckDyEdi9WJqaVoAauyXIYC09YbzvtjiWkkc2qI8JTPuheTi";
    //요청 키워드
    private final String keywords = "백엔드";
    //직업/직종 -> 산업/업종 코드표(참고:사람인 api 코드표 페이지)
    //402:서버,네트워크,보안  404:웹개발  407:응용프로그램개발  408:시스템개발  410:통신,모바  413:퍼블리싱,ui개발  417:인공지능,빅데이터
    private final String job_category = "404+410+402+407+408+413+417";
    //근무 지역 -> 서울전체, 경기전체, 인천전체
    private final String loc_cd = "101000+102000+108000";
    //필드(부가항목 - 날짜/시간형식의 게시일, 날짜/시간형식의 마감일시, 업직종키워드 코드, 조회수/지원자수/댓글수
    private final String fields = "posting-date+expiration-date+keyword-code+count";

    public JSONObject recruitApiList(PageDto pageDto) {
        StringBuffer result = new StringBuffer();
        JSONObject jsonObject = new JSONObject();
        int currentPageNo = pageDto.getCurrentPageNo()-1;
        String keyword = pageDto.getSearchKeyword();
        if(keyword == null){
            keyword = "";
        }
        System.out.println("pagedto : "+pageDto);
//        int recordsPerPage = pageDto.getRecordsPerPage();
        try {
            StringBuilder urlBuilder = new StringBuilder(requestUrl);

            urlBuilder.append("?" + URLEncoder.encode("access-key","UTF-8")+"="+serviceKey);
            urlBuilder.append("&" + URLEncoder.encode("job_category","UTF-8")+"="+job_category);
            urlBuilder.append("&" + URLEncoder.encode("loc_cd","UTF-8")+"="+loc_cd);
            urlBuilder.append("&" + URLEncoder.encode("fields","UTF-8")+"="+fields);
            urlBuilder.append("&" + URLEncoder.encode("start","UTF-8")+"="+currentPageNo);
            urlBuilder.append("&" + URLEncoder.encode("keywords","UTF-8")+"="+URLEncoder.encode(keyword,"UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("keywords","UTF-8")+"="+URLEncoder.encode("신입","UTF-8"));
//            urlBuilder.append("&" + URLEncoder.encode("count","UTF-8")+"="+recordsPerPage);

            log.info("pageDto >>> {}",pageDto.toString());
            URL url = new URL(urlBuilder.toString());
            log.info("url>>>>{}",url);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");

            BufferedReader br;

            if (urlConnection.getResponseCode() >= 200 && urlConnection.getResponseCode() <= 300) {
                br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(),"UTF-8"));
            }else {
                br = new BufferedReader(new InputStreamReader(urlConnection.getErrorStream(),"UTF-8"));
            }

            String returnLine;
            while((returnLine = br.readLine()) != null) {
                result.append(returnLine + "\n");
                System.out.println(br.readLine());
            }
            br.close();
            urlConnection.disconnect();

            JSONParser jsonParser = new JSONParser();
            jsonObject = (JSONObject) jsonParser.parse(result.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }


}
