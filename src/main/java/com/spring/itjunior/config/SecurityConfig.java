package com.spring.itjunior.config;

import com.spring.itjunior.config.auth.PrincipalDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(prePostEnabled = true) //특정 주소로 접근하면 권한 및 인증을 미리 체크하겠다.
@EnableWebSecurity //시큐리티 필터 추 = 스프링 시큐리티가 활성화가 되어있고, 그에 해당하는 설정들을 현재 파일에서 하겠다.
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private PrincipalDetailsService principalDetailsService;

    @Autowired
    public SecurityConfig(PrincipalDetailsService principalDetailsService) {
        this.principalDetailsService = principalDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder encodePWD() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable() //csrf토큰 비활성 (테스트시 걸어두는게 좋음)
                .authorizeRequests()
                    .antMatchers("/auth/**","/boards/**","/js/**","/css/**","/image/**","/","/font/**")
                    .permitAll()  // /auth로 시작하는 모든 매핑에 대하여 허용한다.
                    .anyRequest()
                    .authenticated() //허용을 제외한 나머지 모든 매핑은 인증을 받아야 진입 가능하다.
                .and()
                    .formLogin()
                    .loginPage("/auth/loginForm")
                    .loginProcessingUrl("/auth/loginProc") //스프링 시큐리티가 해당 주소로 요청오는 로그인을 가로채서 대신 로그인 해준다.
                    .usernameParameter("userId")
                    .defaultSuccessUrl("/");

    }

    //암호화된 비번 체킹 메소드 구현 안해도 security가 이 메서드를 default로 생각하여 해쉬된 비밀번호 자동 비교 해줌.
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.userDetailsService(principalDetailsService).passwordEncoder(encodePWD());
//    }
}
