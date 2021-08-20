package com.spring.itjunior.config.auth;

import com.spring.itjunior.domain.Member;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

@Getter
public class PrincipalDetails implements UserDetails, OAuth2User {

    private Member member; //콤포지션 : 객체를 품고 있는 것
    private Map<String, Object> attributes;
    private boolean isFirstOauthLogin; //oauth로그인이 첫번째 인지 확인시켜주는 변수

    //일반 로그인
    public PrincipalDetails(Member member) {
        this.member = member;
    }
    //Oauth로그인
    public PrincipalDetails(Member member, Map<String,Object> attributes, boolean isFirstOauthLogin) {
        this.member = member;
        this.attributes = attributes;
        this.isFirstOauthLogin = isFirstOauthLogin;
    }

    @Override
    public String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getUserId();
    }

    //계정이 만료되지 않았는지 리턴한다 (true:만료안됨)
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //계정이 잠겼는지 안잠겨있는지 확인 (true : 안잠겨있음)
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //비밀번호가 만료되지 않았는지 리턴한다. (true:만료안됨)
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //계정이 활성화(사용가능)인지를 리턴한다. (true : 활성화)
    @Override
    public boolean isEnabled() {
        return true;
    }

    //계정이 갖고있는 권한 목록을 리턴한다.(권한이 여러개 있을수 있어서 루프를 돌아야 하는데 우리는 한개만)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collectors = new ArrayList<>();
        collectors.add(() -> {
            return "ROLE_" + member.getRole();
        });

        return collectors;
    }

    @Override
    public Map<String, Object> getAttributes() {
        return attributes;
    }

    @Override
    public String getName() {
        return null;
    }
}
