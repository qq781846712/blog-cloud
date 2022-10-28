package com.blank.system.service.impl;

import com.blank.system.repository.SysOperLogRepository;
import com.blank.system.service.ISysOperLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * 操作日志 服务层处理
 *
 * @author ruoyi
 */
@RequiredArgsConstructor
@Service
public class SysOperLogServiceImpl implements ISysOperLogService {

    private final SysOperLogRepository baseMapper;


}
