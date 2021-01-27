package org.amall.dubbo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource({"classpath:springboot-dubbo-config.xml"})
@SpringBootApplication
public class AmallDubboServiceImplApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmallDubboServiceImplApplication.class,args);
    }
}
