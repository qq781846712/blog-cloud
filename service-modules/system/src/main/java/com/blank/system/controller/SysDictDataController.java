package com.blank.system.controller;

import com.blank.common.core.web.controller.BaseController;
import com.blank.system.service.ISysDictDataService;
import com.blank.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 数据字典信息
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/dict/data")
public class SysDictDataController extends BaseController {

    private final ISysDictDataService dictDataService;
    private final ISysDictTypeService dictTypeService;

}
