package com.spring.itjunior.member;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void loginWithEnumTest() {
        Member member = new Member();
        member.setUserId("AdminDoom");
        member.setEmail("swannnt@nate.com");
        member.setPassword("1234");
        member.setNickname("관리자");
        member.setName("김재복");
        member.setRole(Role.ADMIN);

        boolean result = memberService.saveMemberInfo(member);

        System.out.println("결과는 "+result+"입니다.");
    }
}
