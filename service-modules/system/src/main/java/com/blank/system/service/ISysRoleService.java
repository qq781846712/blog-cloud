package com.blank.system.service;

import com.blank.system.api.model.dto.RoleDTO;
import com.blank.system.domain.vo.RoleVo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * 角色业务层
 */
public interface ISysRoleService {

    Page<RoleVo> selectList(RoleDTO roleDTO, Pageable pageable);
}
