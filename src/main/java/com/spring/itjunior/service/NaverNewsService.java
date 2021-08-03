package com.spring.itjunior.service;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


@Service
public class NaverNewsService {

    final String clientId = "cva86oC3kaQ2yLpsbcQg";//애플리케이션 클라이언트 아이디값";
    final String clientSecret = "TZwK3cWUma";//애플리케이션 클라이언트 시크릿값";

    public JSONObject newsApiList() {
        JSONObject jsonObject = new JSONObject();
        try {
            String text = URLEncoder.encode("아이티", "UTF-8"); //검색어";
            String apiURL = "https://openapi.naver.com/v1/search/news.json?query="+ text + "&display=10&start=1&sort=date"; // 뉴스의 json 결과
            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);
            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            System.out.println(response.toString());
            JSONParser jsonParser = new JSONParser();

            jsonObject = (JSONObject) jsonParser.parse(response.toString());
        } catch (Exception e) {

            System.out.println(e);
        }
        return jsonObject;
    }

}
