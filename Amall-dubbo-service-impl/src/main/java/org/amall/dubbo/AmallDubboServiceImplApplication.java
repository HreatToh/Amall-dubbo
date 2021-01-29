package org.amall.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("org.amall.dubbo.mapper")
@SpringBootApplication
@EnableDubboConfiguration
public class AmallDubboServiceImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboServiceImplApplication.class,args);
    }
}
