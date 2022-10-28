package com.blank.system.service.impl;

import com.blank.system.repository.SysDictDataRepository;
import com.blank.system.service.ISysDictDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 字典 业务层处理
 */
@RequiredArgsConstructor
@Service
public class SysDictDataServiceImpl implements ISysDictDataService {

    private final SysDictDataRepository baseMapper;
}
