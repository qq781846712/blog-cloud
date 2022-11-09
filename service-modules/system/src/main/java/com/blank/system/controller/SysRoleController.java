package com.blank.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blank.common.core.domain.R;
import com.blank.common.core.web.controller.BaseController;
import com.blank.system.api.domain.SysRole;
import com.blank.system.service.ISysRoleService;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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


    /**
     * 查询角色信息列表
     */
    @SaCheckPermission("system:role:list")
    @GetMapping("/list")
    public R<List<SysRole>> list() {
        return null;
    }

}

