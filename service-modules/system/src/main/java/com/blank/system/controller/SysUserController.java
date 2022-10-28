package com.blank.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blank.common.core.web.controller.BaseController;
import com.blank.system.api.domain.SysUser;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class SysUserController extends BaseController {

    private final ISysUserService userService;

    /**
     * 获取用户列表
     */
    @SaCheckPermission("system:user:list")
    @GetMapping("/list")
    public Page<SysUser> list(SysUser user, PageRequest pageRequest) {
        return userService.selectPageUserList(user, pageRequest);
    }

}
