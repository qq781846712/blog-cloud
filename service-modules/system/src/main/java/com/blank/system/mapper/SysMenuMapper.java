package com.blank.system.mapper;

import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysMenu;
import org.springframework.stereotype.Repository;

/**
 * 菜单表 数据层
 */
@Repository
public interface SysMenuMapper extends BaseMapperPlus<SysMenuMapper, SysMenu, SysMenu> {

}
