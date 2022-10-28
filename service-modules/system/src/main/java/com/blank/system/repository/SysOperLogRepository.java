package com.blank.system.repository;

import com.blank.system.api.domain.SysOperLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 操作日志 数据层
 */
@Repository
public interface SysOperLogRepository extends JpaRepository<SysOperLog, Long>, JpaSpecificationExecutor<SysOperLog> {

}
