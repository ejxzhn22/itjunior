package com.spring.itjunior.config.oauth.provider;

import java.util.Map;


public class NaverUserInfo implements OAuth2UserInfo{
    private Map<String,Object> attributes; //getAttributes()

    public NaverUserInfo(Map<String,Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "naver";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("id").toString().replace("-",""); //64자 이내의 providerId값에 "-"를 제외시킨다.
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return (String) attributes.get("name")+",WelcomeToITJuniorUser";
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("nickname");
    }

}
