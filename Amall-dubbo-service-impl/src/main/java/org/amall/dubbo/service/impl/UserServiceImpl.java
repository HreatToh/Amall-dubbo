package org.amall.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import org.amall.dubbo.service.UserService;

/**设置service的dubbo的超时时间**/
@Service(timeout = 6000)
public class UserServiceImpl extends BaseServiceImpl implements UserService {

}
