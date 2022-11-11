package com.blank.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blank.common.core.utils.StringUtils;
import com.blank.common.mybatis.core.page.PageQuery;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.system.api.domain.SysRole;
import com.blank.system.domain.bo.RoleBo;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.mapper.SysRoleMapper;
import com.blank.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 角色 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper baseMapper;

    @Override
    public TableDataInfo<RoleVo> customPageList(RoleBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<SysRole> lqw = buildQueryWrapper(bo);
        Page<RoleVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        baseMapper.selectList(lqw);
        return TableDataInfo.build(result);
    }

    @Override
    public int update(SysRole sysRole) {
        return baseMapper.updateById(sysRole);
    }

    private LambdaQueryWrapper<SysRole> buildQueryWrapper(RoleBo bo) {
        LambdaQueryWrapper<SysRole> lqw = Wrappers.lambdaQuery();
        lqw.eq(bo.getRoleId() != null, SysRole::getRoleId, bo.getRoleId());
        lqw.like(StringUtils.isNotBlank(bo.getRoleName()), SysRole::getRoleName, bo.getRoleName());
        lqw.like(StringUtils.isNotBlank(bo.getRoleKey()), SysRole::getRoleKey, bo.getRoleKey());
        lqw.eq(StringUtils.isNotBlank(bo.getStatus()), SysRole::getStatus, bo.getStatus());
        lqw.orderByAsc(SysRole::getRoleSort);
        return lqw;
    }
}
