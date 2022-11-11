package com.blank.system.service.impl;

import com.blank.system.mapper.SysDictDataMapper;
import com.blank.system.mapper.SysDictTypeMapper;
import com.blank.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    private final SysDictTypeMapper baseMapper;
    private final SysDictDataMapper dictDataMapper;
}
