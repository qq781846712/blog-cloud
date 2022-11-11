package com.blank.system.controller;

import com.blank.common.core.domain.R;
import com.blank.common.core.web.controller.BaseController;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.system.api.domain.SysRole;
import com.blank.system.domain.bo.RoleBo;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.service.ISysRoleService;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    @PostMapping(value = "/list", produces = "application/json;charset=UTF-8")
    public R<TableDataInfo<RoleVo>> page(HttpServletRequest request, @RequestBody RoleBo bo) {
        return R.ok(roleService.customPageList(bo, bo));
    }

    /**
     * 根据 roleId 修改状态
     */
    @GetMapping(value = "/updateStatus/{roleId}/{status}")
    public R<Integer> page(@PathVariable Long roleId, @PathVariable String status) {
        SysRole sysRole = new SysRole(roleId);
        sysRole.setStatus(status);

        int count = roleService.update(sysRole);
        if (count > 0) {
            return R.ok();
        }
        return R.fail();
    }
}

