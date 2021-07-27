package com.spring.itjunior.config.oauth.provider;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

@Log4j2
@NoArgsConstructor
public class KakaoUserInfo implements OAuth2UserInfo{
    private Map<String,Object> attributes; //getAttributes()
    private Map<String, Object> kakao_account;
    private Map<String, Object> profile;

    //kakao_account안의 정보와 그안에 profile안에 정보를 가져오기 위해서 부분부분 키값을 담아 Map형태로 정리해둔다.
    public KakaoUserInfo(Map<String,Object> attributes, Map<String,Object> kakao_account, Map<String,Object> profile) {
        this.attributes = attributes;
        this.kakao_account=kakao_account;
        this.profile=profile;
    }

//    private Map<String, Object> kakao_account = (Map<String, Object>) attributes.get("kakao_account");
//    private Map<String, Object> profile = (Map<String, Object>) kakao_account.get("profile");


    @Override
    public String getProvider() {
        return "kakao";
    }

    @Override
    public String getProviderId() {
        return String.valueOf(attributes.get("id")); //naver id는 Long타입이기 때문에 String 타입으로 강제 변환.
    }

    @Override
    public String getEmail() {
        return (String) kakao_account.get("email");
    }

    @Override
    public String getName() {
        return (String) profile.get("nickname");
    }

    @Override
    public String getNickname() {
        return (String) profile.get("nickname");
    }

}
