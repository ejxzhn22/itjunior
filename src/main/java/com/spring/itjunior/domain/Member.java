package com.spring.itjunior.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Member {

    private int member_idx;
    private String userId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private DeleteYN delete_yn;
    private LocalDateTime create_time;
    private LocalDateTime last_login_time;
    private Role role;

}
