package com.spring.itjunior.service;

import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Log4j2
@Service
public class ApiService {

    private final String requestUrl = "https://oapi.saramin.co.kr/job-search";
    private final String serviceKey = "9YSJckDyEdi9WJqaVoAauyXIYC09YbzvtjiWkkc2qI8JTPuheTi";

    public String recruitApiList() {
        StringBuffer result = new StringBuffer();
        try {
            StringBuilder urlBuilder = new StringBuilder(requestUrl);

            urlBuilder.append("?" + URLEncoder.encode("access-key","UTF-8")+"="+serviceKey);
            urlBuilder.append("&" + URLEncoder.encode("keywords","UTF-8")+"="+URLEncoder.encode("백엔드","UTF-8"));
            urlBuilder.append("&" + URLEncoder.encode("job_category","UTF-8")+"=404+410+402+407+408+413+417");
            urlBuilder.append("&" + URLEncoder.encode("loc_cd","UTF-8")+"=101000+102000+108000");
            urlBuilder.append("&" + URLEncoder.encode("fields","UTF-8")+"=posting-date+expiration-date+keyword-code+count");

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

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result.toString();
    }


}
