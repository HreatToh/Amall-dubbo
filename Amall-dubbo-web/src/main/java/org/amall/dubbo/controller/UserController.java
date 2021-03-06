package org.amall.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.amall.dubbo.common.DubboConfig;
import org.amall.dubbo.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
public class UserController {


    @Reference(version = DubboConfig.SERVICE_VERSION,check = DubboConfig.SERVICE_CHECK)
    private UserService userService;


    @GetMapping("test")
    @ResponseBody
    public List test(@RequestParam Map<String,Object> params) throws Exception {
        return userService.list(params);
    }
}
