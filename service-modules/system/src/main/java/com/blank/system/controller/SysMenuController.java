package com.blank.system.controller;

import cn.hutool.json.JSONUtil;
import com.blank.common.core.web.controller.BaseController;
import com.blank.system.service.ISysMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 菜单信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/menu")
public class SysMenuController extends BaseController {

    private final ISysMenuService menuService;

    @GetMapping("getAsyncRoutes")
    public Map getAsyncRoutes() {
        return JSONUtil.toBean("{\n" +
                "  success: true,\n" +
                "  data: [{\n" +
                "  path: \"/system\",\n" +
                "  meta: {\n" +
                "    icon: \"setting\",\n" +
                "    title: \"系统管理\",\n" +
                "    rank: 11\n" +
                "  },\n" +
                "  children: [\n" +
                "    {\n" +
                "      path: \"/system/user/index\",\n" +
                "      name: \"User\",\n" +
                "      meta: {\n" +
                "        icon: \"flUser\",\n" +
                "        title: \"用户管理\",\n" +
                "        roles: [\"admin\"]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      path: \"/system/role/index\",\n" +
                "      name: \"Role\",\n" +
                "      meta: {\n" +
                "        icon: \"role\",\n" +
                "        title: \"角色管理\",\n" +
                "        roles: [\"admin\"]\n" +
                "      }\n" +
                "    },\n" +
                "    {\n" +
                "      path: \"/system/dict\",\n" +
                "      component: \"/system/dict/index\",\n" +
                "      name: \"Dict\",\n" +
                "      meta: {\n" +
                "        icon: \"dict\",\n" +
                "        title: \"字典管理\",\n" +
                "        keepAlive: true,\n" +
                "        roles: [\"admin\"]\n" +
                "      }\n" +
                "    }\n" +
                "  ]\n" +
                "}]}", Map.class);
    }
}
