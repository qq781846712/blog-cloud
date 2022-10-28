package com.blank.system.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.blank.common.core.exception.ServiceException;
import com.blank.system.api.domain.SysUser;
import com.blank.system.repository.SysUserRepository;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * 用户 业务层处理
 */
@Slf4j
@RequiredArgsConstructor
@Service
public class SysUserServiceImpl implements ISysUserService {

    private final SysUserRepository baseMapper;

    @Override
    public Page<SysUser> selectPageUserList(SysUser user, PageRequest pageRequest) {
        return baseMapper.findByUserNameLikeIgnoreCaseAndNickNameLikeIgnoreCaseOrderByUserIdDesc(user.getUserName(), user.getNickName(), pageRequest);
    }

    /**
     * 校验用户是否允许操作
     *
     * @param user 用户信息
     */
    @Override
    public void checkUserAllowed(SysUser user) {
        if (ObjectUtil.isNotNull(user.getUserId()) && user.isAdmin()) {
            throw new ServiceException("不允许操作超级管理员用户");
        }
    }

    /**
     * 校验用户是否有数据权限
     *
     * @param userId 用户id
     */
    @Override
    public void checkUserDataScope(Long userId) {
        /*if (!LoginHelper.isAdmin()) {
            SysUser user = new SysUser();
            user.setUserId(userId);
            List<SysUser> users = this.selectUserList(user);
            if (CollUtil.isEmpty(users)) {
                throw new ServiceException("没有权限访问用户数据！");
            }
        }*/
    }

}
