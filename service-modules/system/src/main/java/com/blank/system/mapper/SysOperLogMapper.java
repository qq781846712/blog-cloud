package com.blank.system.mapper;

import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysOperLog;
import org.springframework.stereotype.Repository;

/**
 * 操作日志 数据层
 */
@Repository
public interface SysOperLogMapper extends BaseMapperPlus<SysOperLogMapper, SysOperLog, SysOperLog> {

}
