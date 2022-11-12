package com.blank.system.dubbo;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ObjectUtil;
import com.blank.common.core.enums.UserStatus;
import com.blank.common.core.exception.user.NotFoundUserException;
import com.blank.common.core.exception.user.UserException;
import com.blank.system.api.RemoteUserService;
import com.blank.system.api.domain.SysUser;
import com.blank.system.api.model.LoginUser;
import com.blank.system.api.model.dto.RoleDTO;
import com.blank.system.service.ISysPermissionService;
import com.blank.system.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 操作日志记录
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteUserServiceImpl implements RemoteUserService {

    private final ISysUserService userService;
    private final ISysPermissionService permissionService;

    @Override
    public LoginUser getUserInfo(String username) throws UserException {
        SysUser sysUser = userService.selectUserByUserName(username);
        if (ObjectUtil.isNull(sysUser)) {
            throw new UserException("user.not.exists", username);
        }
        if (UserStatus.DISABLE.getCode().equals(sysUser.getStatus())) {
            throw new UserException("user.blocked", username);
        }
        // 此处可根据登录用户的数据不同 自行创建 loginUser
        return buildLoginUser(sysUser);
    }

    /**
     * 构建登录用户
     */
    private LoginUser buildLoginUser(SysUser user) {
        if (user == null) {
            throw new NotFoundUserException();
        }
        LoginUser loginUser = new LoginUser();
        loginUser.setUserId(user.getUserId());
        loginUser.setUsername(user.getUserName());
        loginUser.setPassword(user.getPassword());
        loginUser.setUserType(user.getUserType());
        loginUser.setMenuPermission(permissionService.getMenuPermission(user));
        loginUser.setRolePermission(permissionService.getRolePermission(user));
        List<RoleDTO> roles = BeanUtil.copyToList(user.getRoles(), RoleDTO.class);
        loginUser.setRoles(roles);
        return loginUser;
    }
}
