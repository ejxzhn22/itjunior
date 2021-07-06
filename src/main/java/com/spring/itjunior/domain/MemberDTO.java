package com.spring.itjunior.domain;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberDTO {

    private int member_idx;
    private String userId;
    private String email;
    private String password;
    private String nickname;
    private String name;
    private String delete_yn;
    private LocalDateTime create_time;
    private LocalDateTime last_login_time;
    //private Enum role

}
