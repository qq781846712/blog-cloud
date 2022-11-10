package com.blank.system.repository;

import com.blank.system.api.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * 角色表 数据层
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {


}
