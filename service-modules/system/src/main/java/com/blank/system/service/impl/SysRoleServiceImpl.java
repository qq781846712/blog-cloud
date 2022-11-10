package com.blank.system.service.impl;

import com.blank.system.api.domain.QSysRole;
import com.blank.system.api.domain.SysRole;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.repository.SysRoleRepository;
import com.blank.system.service.ISysRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 角色 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysRoleServiceImpl implements ISysRoleService {

    private final SysRoleRepository sysRoleRepository;

    public List<RoleVo> selectList(PageRequest pageRequest) {
        QSysRole sysRole = QSysRole.sysRole;
        Page<SysRole> page = sysRoleRepository.findAll(Pageable pageable);
        return null;
    }

}
