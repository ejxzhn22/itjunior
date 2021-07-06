package com.spring.itjunior;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.sql.Connection;


@SpringBootTest
public class DataSourceTests {

    @Autowired
    private DataSource dataSource;

    @Test
    public void testConnection() {
        try {
            Connection con = dataSource.getConnection();
            System.out.println("연결 완료!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
