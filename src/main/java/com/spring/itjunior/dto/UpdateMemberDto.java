package com.spring.itjunior.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMemberDto {

    @NotNull(message = "유저 idx가 존재하지 않습니다.")
    private int member_idx;

    @NotNull(message = "비밀번호 키값이 없습니다.")
    private String password;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @NotBlank(message = "유저 이름을 입력하세요.")
    @Size(max = 25, message = "유저 이름 길이(25)를 초과하였습니다.")
    private String name;

    @NotBlank(message = "유저 닉네임을 입력하세요.")
    private String nickname;

    @Builder
    public UpdateMemberDto(int member_idx, String password, String email,String name, String nickname) {
        this.member_idx = member_idx;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }

}
