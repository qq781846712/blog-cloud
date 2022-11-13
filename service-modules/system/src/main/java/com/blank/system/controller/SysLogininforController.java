package com.blank.system.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.blank.common.core.constant.CacheConstants;
import com.blank.common.core.domain.R;
import com.blank.common.core.web.controller.BaseController;
import com.blank.common.log.annotation.Log;
import com.blank.common.log.enums.BusinessType;
import com.blank.common.mybatis.core.page.PageQuery;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.common.redis.utils.RedisUtils;
import com.blank.system.api.domain.SysLogininfor;
import com.blank.system.service.ISysLogininforService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * 系统访问记录
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/logininfor")
public class SysLogininforController extends BaseController {

    private final ISysLogininforService logininforService;

    /**
     * 查询系统访问记录列表
     */
    @SaCheckPermission("system:logininfor:list")
    @GetMapping("/list")
    public TableDataInfo<SysLogininfor> list(SysLogininfor logininfor, PageQuery pageQuery) {
        return logininforService.selectPageLogininforList(logininfor, pageQuery);
    }

    /**
     * 删除系统访问记录
     *
     * @param infoIds 记录ID串
     */
    @SaCheckPermission("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{infoIds}")
    public R<Void> remove(@PathVariable Long[] infoIds) {
        return toAjax(logininforService.deleteLogininforByIds(infoIds));
    }

    /**
     * 清空系统访问记录
     */
    @SaCheckPermission("system:logininfor:remove")
    @Log(title = "登录日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/clean")
    public R<Void> clean() {
        logininforService.cleanLogininfor();
        return R.ok();
    }

    @SaCheckPermission("system:logininfor:unlock")
    @Log(title = "账户解锁", businessType = BusinessType.OTHER)
    @GetMapping("/unlock/{userName}")
    public R<Void> unlock(@PathVariable("userName") String userName) {
        String loginName = CacheConstants.PWD_ERR_CNT_KEY + userName;
        if (RedisUtils.hasKey(loginName)) {
            RedisUtils.deleteObject(loginName);
        }
        return R.ok();
    }

}
