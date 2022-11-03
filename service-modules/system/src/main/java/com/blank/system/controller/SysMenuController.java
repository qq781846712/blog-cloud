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
                "    path: \"/permission\",\n" +
                "    meta: {\n" +
                "      title: \"menus.permission\",\n" +
                "      icon: \"lollipop\",\n" +
                "      rank: 10\n" +
                "    },\n" +
                "    children: [\n" +
                "      {\n" +
                "        path: \"/permission/page/index\",\n" +
                "        name: \"PermissionPage\",\n" +
                "        meta: {\n" +
                "          roles: [\"admin\", \"common\"],\n" +
                "          title: \"menus.permissionPage\"\n" +
                "        }\n" +
                "      },\n" +
                "      {\n" +
                "        path: \"/permission/button/index\",\n" +
                "        name: \"PermissionButton\",\n" +
                "        meta: {\n" +
                "          title: \"menus.permissionButton\",\n" +
                "          roles: [\"admin\", \"common\"],\n" +
                "          auths: [\"btn_add\", \"btn_edit\", \"btn_delete\"]\n" +
                "        }\n" +
                "      }\n" +
                "    ]\n" +
                "  }]\n" +
                "}", Map.class);
    }
}
