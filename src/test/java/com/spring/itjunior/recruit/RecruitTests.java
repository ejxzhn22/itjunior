package com.spring.itjunior.recruit;

import com.spring.itjunior.service.RecruitService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class RecruitTests {
    @Autowired
    private RecruitService recruitService;

    @Test
    public void testBySaveRecruitInfo() {

    }
}
