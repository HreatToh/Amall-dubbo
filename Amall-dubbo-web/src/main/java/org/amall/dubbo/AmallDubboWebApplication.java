package org.amall.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:springboot-dubbo-config.xml"})
@SpringBootApplication
public class AmallDubboWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboWebApplication.class,args);
    }
}
