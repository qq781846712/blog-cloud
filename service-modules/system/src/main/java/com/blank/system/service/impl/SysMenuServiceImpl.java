package com.blank.system.service.impl;

import com.blank.system.repository.SysMenuRepository;
import com.blank.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 菜单 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysMenuServiceImpl implements ISysMenuService {

    private final SysMenuRepository baseMapper;
}
