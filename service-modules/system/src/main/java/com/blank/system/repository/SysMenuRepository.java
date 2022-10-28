package com.blank.system.repository;

import com.blank.system.api.domain.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 菜单表 数据层
 */
@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu, Long>, JpaSpecificationExecutor<SysMenu> {

}
