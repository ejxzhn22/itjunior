package com.spring.itjunior.config.oauth.provider;

import lombok.NoArgsConstructor;

import java.util.Map;

public class GoogleUserInfo implements OAuth2UserInfo{

    private Map<String,Object> attributes; //getAttributes()

    public GoogleUserInfo(Map<String,Object> attributes) {
        this.attributes = attributes;
    }

    @Override
    public String getProvider() {
        return "google";
    }

    @Override
    public String getProviderId() {
        return (String) attributes.get("sub");
    }

    @Override
    public String getEmail() {
        return (String) attributes.get("email");
    }

    @Override
    public String getName() {
        return "WelcomeToITJuniorUser";
    }

    @Override
    public String getNickname() {
        return (String) attributes.get("name").toString().replace("-","");
    }
}
