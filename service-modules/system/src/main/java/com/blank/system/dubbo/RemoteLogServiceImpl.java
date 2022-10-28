package com.blank.system.dubbo;

import com.blank.system.api.RemoteLogService;
import com.blank.system.api.domain.SysLogininfor;
import com.blank.system.api.domain.SysOperLog;
import com.blank.system.service.ISysLogininforService;
import com.blank.system.service.ISysOperLogService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteLogServiceImpl implements RemoteLogService {

    private final ISysOperLogService operLogService;
    private final ISysLogininforService logininforService;

    @Override
    public Boolean saveLog(SysOperLog sysOperLog) {
        return true;
    }

    @Override
    public Boolean saveLogininfor(SysLogininfor sysLogininfor) {
        return true;
    }
}
