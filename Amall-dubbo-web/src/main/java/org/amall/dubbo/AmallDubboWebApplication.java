package org.amall.dubbo;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceAutoConfigure;
import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
@EnableDubboConfiguration
public class AmallDubboWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboWebApplication.class,args);
    }
}
