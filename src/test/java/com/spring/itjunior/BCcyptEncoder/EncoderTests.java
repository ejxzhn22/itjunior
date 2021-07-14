package com.spring.itjunior.BCcyptEncoder;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootTest
public class EncoderTests {

    @Test
    public void encodingTest() {
        String encPassword = new BCryptPasswordEncoder().encode("1234");
        System.out.println("1234 해쉬 >>> "+encPassword);
    }
}
