package com.blank.system.repository;

import com.blank.system.api.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * 用户表 数据层
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUser, Long>, JpaSpecificationExecutor<SysUser> {

    Page<SysUser> findByUserNameLikeIgnoreCaseAndNickNameLikeIgnoreCaseOrderByUserIdDesc(@Nullable String userName, @Nullable String nickName, Pageable pageable);

    SysUser findByUserNameEquals(String userName);

}
