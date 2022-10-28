package com.blank.system.repository;

import com.blank.system.api.domain.SysLogininfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 系统访问日志情况信息 数据层
 */
@Repository
public interface SysLogininforRepository extends JpaRepository<SysLogininfor, Long>, JpaSpecificationExecutor<SysLogininfor> {

}
