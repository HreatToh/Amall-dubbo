package org.amall.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class AmallDubboWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboWebApplication.class,args);
    }
}
