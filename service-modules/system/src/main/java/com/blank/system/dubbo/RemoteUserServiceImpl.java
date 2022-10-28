package com.blank.system.dubbo;

import com.blank.common.core.exception.user.UserException;
import com.blank.system.api.RemoteUserService;
import com.blank.system.api.model.LoginUser;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteUserServiceImpl implements RemoteUserService {

    private final ISysUserService userService;

    @Override
    public LoginUser getUserInfo(String username) throws UserException {
        return null;
    }

}
