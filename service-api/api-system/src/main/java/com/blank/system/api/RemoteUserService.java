package com.blank.system.api;

import com.blank.common.core.exception.user.UserException;
import com.blank.system.api.model.LoginUser;

/**
 * 用户服务
 */
public interface RemoteUserService {

    /**
     * 通过用户名查询用户信息
     *
     * @param username 用户名
     * @return 结果
     */
    LoginUser getUserInfo(String username) throws UserException;

}
