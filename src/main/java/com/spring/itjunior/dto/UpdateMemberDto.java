package com.spring.itjunior.dto;

import lombok.*;

import javax.validation.constraints.*;


@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateMemberDto {

    @NotNull(message = "유저 idx가 존재하지 않습니다.")
    private int member_idx;

    private String password;

    @NotBlank(message = "이메일을 입력하세요.")
    @Email(message = "이메일 형식이 아닙니다.")
    private String email;

    @Pattern(regexp = "^[a-zA-Z가-힣]{1,20}$", message = "영문,한글 사용 가능. 1 ~ 20자.")
    @NotBlank(message = "유저 이름을 입력하세요.")
    @Size(max = 20, message = "유저 이름 길이(20)를 초과하였습니다.")
    private String name;

    @Pattern(regexp = "^[a-zA-Z가-힣0-9]{1,20}$", message = "영문,한글,숫자 사용 가능. 1 ~ 20자.")
    @NotBlank(message = "유저 닉네임을 입력하세요.")
    @Size(max = 20, message = "유저 이름 길이(20)를 초과하였습니다.")
    private String nickname;

    @Builder
    public UpdateMemberDto(int member_idx, String password, String email, String name, String nickname) {
        this.member_idx = member_idx;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }

}
