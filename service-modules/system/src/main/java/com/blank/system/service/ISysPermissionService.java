package com.blank.system.service;

import com.blank.system.api.domain.SysUser;

import java.util.Set;

/**
 * 权限信息 服务层
 *
 *
 */
public interface ISysPermissionService {
    /**
     * 获取角色数据权限
     *
     * @param user 用户Id
     * @return 角色权限信息
     */
    Set<String> getRolePermission(SysUser user);

    /**
     * 获取菜单数据权限
     *
     * @param user 用户
     * @return 菜单权限信息
     */
    Set<String> getMenuPermission(SysUser user);
}
