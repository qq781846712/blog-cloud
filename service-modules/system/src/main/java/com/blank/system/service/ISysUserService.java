package com.blank.system.service;

import com.blank.system.api.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * 用户 业务层
 */
public interface ISysUserService {

    Page<SysUser> selectPageUserList(SysUser user, PageRequest pageRequest);

    void checkUserAllowed(SysUser user);

    void checkUserDataScope(Long userId);

    SysUser findByUserNameEquals(String username);
}
