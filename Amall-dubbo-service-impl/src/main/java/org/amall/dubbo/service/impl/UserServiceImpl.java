package org.amall.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.amall.dubbo.common.DubboConfig;
import org.amall.dubbo.service.UserService;
import org.springframework.stereotype.Component;

/**设置service的dubbo的超时时间**/
@Service(timeout = DubboConfig.SERVICE_TIME_OUT,version = DubboConfig.SERVICE_VERSION,interfaceClass = UserService.class)
@Component
public class UserServiceImpl extends BaseServiceImpl implements UserService {



}
