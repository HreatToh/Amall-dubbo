package org.amall.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import org.amall.dubbo.service.UserService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {


    @Reference
    private UserService userService;
}
