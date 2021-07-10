package com.spring.itjunior.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다.
@EnableWebSecurity //시큐리티 필터 추 = 스프링 시큐리티가 활성화가 되어있고, 그에 해당하는 설정들을 현재 파일에서 하겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/auth/**")
                    .permitAll()  // /auth로 시작하는 모든 매핑에 대하여 허용한다.
                    .anyRequest()
                    .authenticated() //허용을 제외한 나머지 모든 매핑은 인증을 받아야 진입 가능하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm");

    }
}
