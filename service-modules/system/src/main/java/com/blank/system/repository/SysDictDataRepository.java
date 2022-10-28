package com.blank.system.repository;

import com.blank.system.api.domain.SysDictData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * 字典表 数据层
 */
@Repository
public interface SysDictDataRepository extends JpaRepository<SysDictData, Long>, JpaSpecificationExecutor<SysDictData> {

}
