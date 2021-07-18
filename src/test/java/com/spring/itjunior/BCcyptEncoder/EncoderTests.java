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

    @Test
    public void encodingEmailPartialTest() {
        String rawEmail = "ufozx1023@nate.com";
        int length = rawEmail.length();
        int partition = 0;
        int partition2 = 0;
        int prefix = 0;
        int suffix = 0;
        for (int i = 0; i < length; i++) {
            if (rawEmail.charAt(i) == '@') {
                partition = i;
            } else if (rawEmail.charAt(i) == '.') {
                partition2 = i;
            }
        }
        System.out.println("partition = "+partition+", partition2 = "+partition2);
        int pre =partition/2;
        int suf = (partition2 - partition)/2;
        System.out.println("pre = "+pre);
        System.out.println("suf = "+suf);
        prefix = partition - pre;
        suffix = partition2 - suf + 1;

        int star = suffix - prefix;
        String starStr = "";
        System.out.println("star >> "+star);
        for (int i = 0; i < star; i++) {
            starStr += "*";
        }
        System.out.println(starStr);
        StringBuffer sb = new StringBuffer();
        sb.append(rawEmail);
        sb.replace(prefix,suffix,starStr);

        System.out.println("결과 >> "+sb);


    }
}
