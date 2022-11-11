package com.blank.system.service.impl;

import com.blank.system.api.model.dto.RoleDTO;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.mapper.SysRoleMapper;
import com.blank.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * 角色 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleMapper sysRoleRepository;

    @Override
    public Page<RoleVo> selectList(RoleDTO roleDTO, Pageable pageable) {

        return null;
    }

}
