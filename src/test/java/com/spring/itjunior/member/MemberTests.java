package com.spring.itjunior.member;

import com.spring.itjunior.domain.MemberDTO;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;


@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void loginWithEnumTest() {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setUserId("AdminDoom");
        memberDTO.setEmail("swannnt@nate.com");
        memberDTO.setPassword("1234");
        memberDTO.setNickname("관리자");
        memberDTO.setName("김재복");
        memberDTO.setRole(Role.ADMIN);

        boolean result = memberService.saveMemberInfo(memberDTO);

        System.out.println("결과는 "+result+"입니다.");
    }
}
