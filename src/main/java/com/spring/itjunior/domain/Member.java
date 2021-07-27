package com.spring.itjunior.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class Member {

    private int member_idx;
    private String userId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private DeleteYN delete_yn; //이용 중단
    private LocalDateTime create_time;
    private LocalDateTime last_login_time;
    private Role role;
    private String uuid;
    private String provider;
    private String providerId;

    @Builder
    public Member(String userId, String email, String password, String nickname, String name, DeleteYN delete_yn, LocalDateTime create_time, LocalDateTime last_login_time, Role role, String uuid, String provider, String providerId) {
        this.userId = userId;
        this.email = email;
        this.password = password;
        this.nickname = nickname;
        this.name = name;
        this.delete_yn = delete_yn;
        this.create_time = create_time;
        this.last_login_time = last_login_time;
        this.role = role;
        this.uuid = uuid;
        this.provider = provider;
        this.providerId = providerId;
    }
}
