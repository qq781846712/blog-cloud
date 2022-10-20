package com.blank.common.log.service;

import org.springframework.stereotype.Service;

/**
 * 异步调用日志服务
 */
@Service
public class AsyncLogService {

    /*@DubboReference
    private RemoteLogService remoteLogService;

    *//**
     * 保存系统日志记录
     *//*
    @Async
    public void saveSysLog(SysOperLog sysOperLog) {
        remoteLogService.saveLog(sysOperLog);
    }*/
}
