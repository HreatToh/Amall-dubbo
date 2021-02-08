package org.amall.dubbo;

import com.alibaba.dubbo.spring.boot.annotation.EnableDubboConfiguration;
import net.hasor.spring.boot.EnableHasor;
import net.hasor.spring.boot.EnableHasorWeb;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableHasor
@EnableHasorWeb
@SpringBootApplication
@EnableDubboConfiguration
public class AmallDubboWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboWebApplication.class,args);
    }
}
