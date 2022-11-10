package com.blank.system.controller;

import com.blank.common.core.domain.R;
import com.blank.common.core.web.controller.BaseController;
import com.blank.system.api.model.dto.RoleDTO;
import com.blank.system.domain.vo.RoleVo;
import com.blank.system.service.ISysRoleService;
import com.blank.system.service.ISysUserService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
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
    @GetMapping("/list")
    public R<Page<RoleVo>> list(RoleDTO roleDTO, Pageable pageable) {
        return R.ok(roleService.selectList(roleDTO, pageable));
    }
}

