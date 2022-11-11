package com.blank.system.mapper;

import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysRole;
import com.blank.system.domain.vo.RoleVo;
import org.springframework.stereotype.Repository;

/**
 * 角色表 数据层
 */
public interface SysRoleMapper extends BaseMapperPlus<SysRoleMapper, SysRole, RoleVo> {


}
