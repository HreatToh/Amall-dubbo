package org.amall.dubbo.service.impl;


import cn.hutool.core.map.MapUtil;
import com.alibaba.dubbo.config.annotation.Reference;
import org.amall.dubbo.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceImplTests {

    @Reference
    UserService userService;

    @Test
    public void contextLoads() throws Exception {
        userService = new UserServiceImpl();
        userService.list(MapUtil.newHashMap());
    }
}
