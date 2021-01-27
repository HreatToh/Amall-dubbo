package org.amall.dubbo.test;
import cn.hutool.core.map.MapUtil;
import org.amall.dubbo.service.UserService;
import org.amall.dubbo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AmallDubboWebApplicationTests {

    @Test
    void contextLoads() throws Exception {
        UserService userService = new UserServiceImpl();
        userService.list(MapUtil.newHashMap());
    }
}
