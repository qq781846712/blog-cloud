package com.blank.system.controller;

import com.blank.common.core.web.controller.BaseController;
import com.blank.system.service.ISysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 操作日志记录
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/operlog")
public class SysOperlogController extends BaseController {

    private final ISysOperLogService operLogService;
}
