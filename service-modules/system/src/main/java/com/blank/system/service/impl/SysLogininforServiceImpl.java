package com.blank.system.service.impl;

import com.blank.system.mapper.SysLogininforMapper;
import com.blank.system.service.ISysLogininforService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 系统访问日志情况信息 服务层处理
 */
@RequiredArgsConstructor
@Service
public class SysLogininforServiceImpl implements ISysLogininforService {

    private final SysLogininforMapper baseMapper;

}
