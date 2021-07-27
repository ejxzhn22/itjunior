package com.spring.itjunior.dto;

import com.spring.itjunior.domain.Member;
import lombok.*;

import javax.validation.constraints.*;


@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class JoinDto {

    @Pattern(regexp = "^[a-z0-9_]{4,50}$",message = "영문소문자,숫자,특수기호(_)먼 사용 가능. 4자이상 50자이하.")
    @NotBlank(message = "유저 아이디를 입력하세요.")
    @Size(max = 20,message = "유저 아이디 길이(20)을 초과하였습니다.")
    private String userId;

    @Pattern(regexp = "^(?=.*[a-zA-Z0-9])(?=.*[a-zA-Z!@#$%^&*])(?=.*[0-9~!@#$%^&*()_+=|?/,.<>;:'\"{}]).{8,16}$", message = "영문대/소문자, 숫자, 특수기호 중 2가지이상 조합. 8 ~ 16자.")
    @NotBlank(message = "비밀번호를 입력하세요. (공백이 있거나 null인 문자.)")
    @Size(min = 8, max = 16, message = "유저 비밀번호 8 ~ 16자.")
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
    public JoinDto(String userId, String password, String email, String name, String nickname) {
        this.userId = userId;
        this.password = password;
        this.email = email;
        this.name = name;
        this.nickname = nickname;
    }

}
