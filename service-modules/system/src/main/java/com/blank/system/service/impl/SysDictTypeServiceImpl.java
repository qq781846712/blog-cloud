package com.blank.system.service.impl;

import com.blank.system.repository.SysDictDataRepository;
import com.blank.system.repository.SysDictTypeRepository;
import com.blank.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysDictTypeServiceImpl implements ISysDictTypeService {

    private final SysDictTypeRepository baseMapper;
    private final SysDictDataRepository dictDataMapper;
}
