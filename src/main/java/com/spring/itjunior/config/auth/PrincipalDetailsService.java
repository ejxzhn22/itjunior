package com.spring.itjunior.config.auth;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.mapper.MemberMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private MemberMapper memberMapper;

    @Autowired
    public PrincipalDetailsService(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("=================사용자 로그인====================");
        Member principal = memberMapper.selectMemberByUserId(username);
        if (principal == null) {
            System.out.println("해당 사용자를 찾을 수 없습니다." + username);
            return null;
        }
        memberMapper.updateLoginTime(principal); //로그인 수행 시 last_login_time date 업데이트.

        return new PrincipalDetails(principal); //시큐리티 세션에 유저정보가 저장됨. 해당 메소드를 생성하지 않으면 아이디:user 비번:콘솔창 형태로 계속 사용해야함.
    }
}
