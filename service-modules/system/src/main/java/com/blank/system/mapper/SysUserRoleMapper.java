package com.blank.system.mapper;

import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysUserRole;

import java.util.List;

/**
 * 用户与角色关联表 数据层
 */
public interface SysUserRoleMapper extends BaseMapperPlus<SysUserRoleMapper, SysUserRole, SysUserRole> {

    List<Long> selectUserIdsByRoleId(Long roleId);

}
