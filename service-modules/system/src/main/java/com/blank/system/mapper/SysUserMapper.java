package com.blank.system.mapper;

import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysUser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Repository;

/**
 * 用户表 数据层
 */
@Repository
public interface SysUserMapper extends BaseMapperPlus<SysUserMapper, SysUser, SysUser> {

    Page<SysUser> findByUserNameLikeIgnoreCaseAndNickNameLikeIgnoreCaseOrderByUserIdDesc(@Nullable String userName, @Nullable String nickName, Pageable pageable);

    SysUser findByUserNameEquals(String userName);

}
