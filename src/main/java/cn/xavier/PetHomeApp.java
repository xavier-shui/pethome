package cn.xavier;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Zheng-Wei Shui
 * @date 11/10/2021
 */
@SpringBootApplication
@MapperScan("cn.xavier.*.mapper")
public class PetHomeApp {
    public static void main(String[] args) {
        SpringApplication.run(PetHomeApp.class, args);
    }
}