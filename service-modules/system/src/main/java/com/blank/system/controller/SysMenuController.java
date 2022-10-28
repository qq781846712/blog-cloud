package com.blank.system.controller;

import com.blank.common.core.web.controller.BaseController;
import com.blank.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 菜单信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    private final ISysMenuService menuService;


}
