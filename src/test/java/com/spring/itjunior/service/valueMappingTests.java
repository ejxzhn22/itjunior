package com.spring.itjunior.service;

import com.spring.itjunior.dto.JoinDto;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.Valid;

@SpringBootTest
public class valueMappingTests {
    public static JoinDto joinDto;
    @Test
    public void noSetter(@Valid JoinDto join) {

        JoinDto.builder().userId("uuus").password("1111").name("이야호").nickname("").email("wfe@wfe.ewf").build();
        System.out.println(">>>"+joinDto.toString());

    }
}
