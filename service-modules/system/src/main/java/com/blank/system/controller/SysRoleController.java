package com.blank.system.controller;

import com.blank.common.core.web.controller.BaseController;
import com.blank.system.service.ISysPermissionService;
import com.blank.system.service.ISysRoleService;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 角色信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/role")
public class SysRoleController extends BaseController {

    private final ISysRoleService roleService;
    private final ISysUserService userService;
    private final ISysPermissionService permissionService;


}

