package com.blank.system.service;

import com.blank.common.mybatis.core.page.PageQuery;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.system.api.domain.SysRole;
import com.blank.system.domain.bo.RoleBo;
import com.blank.system.domain.vo.RoleVo;

/**
 * 角色业务层
 */
public interface ISysRoleService {

    TableDataInfo<RoleVo> customPageList(RoleBo bo, PageQuery pageQuery);

    int update(SysRole sysRole);
}
