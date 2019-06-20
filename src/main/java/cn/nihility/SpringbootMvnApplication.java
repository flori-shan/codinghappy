package cn.nihility;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"cn.nihility.mybatis.dao"})
public class SpringbootMvnApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMvnApplication.class, args);
    }

}

