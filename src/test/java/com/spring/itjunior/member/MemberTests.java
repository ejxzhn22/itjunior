package com.spring.itjunior.member;

import com.spring.itjunior.domain.Member;
import com.spring.itjunior.domain.Role;
import com.spring.itjunior.service.MemberService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class MemberTests {

    @Autowired
    private MemberService memberService;

    @Test
    public void loginWithEnumTest() {
        Member member = new Member();
        member.setUserId("doompok");
        member.setEmail("swannnt@nate.com");
        member.setPassword("1234");
        member.setNickname("죠링이");
        member.setName("리재복");
//        member.setRole(Role.ADMIN);

        boolean result = memberService.saveMemberInfo(member);

        System.out.println("결과는 "+result+"입니다.");
    }

    @Test
    public void findByIdx() {

        Member resultMember = memberService.findByIdx(1);
        System.out.println(resultMember.toString());
        Assertions.assertEquals(1,resultMember.getMember_idx());
    }

    @Test
    public void findByUserId() {
        Member memberInfo = memberService.findByIdx(1);

        Member resultMember = memberService.findByUserId(memberInfo.getUserId());
        System.out.println(resultMember.toString());
        Assertions.assertEquals(memberInfo.getUserId(),resultMember.getUserId());
    }
}
