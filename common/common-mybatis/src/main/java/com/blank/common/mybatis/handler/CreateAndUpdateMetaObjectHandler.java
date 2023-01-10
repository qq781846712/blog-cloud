package com.blank.common.mybatis.handler;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpStatus;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.blank.common.core.constant.CommonConstants;
import com.blank.common.core.domain.BaseEntity;
import com.blank.common.core.exception.ServiceException;
import com.blank.common.satoken.utils.LoginHelper;
import com.blank.system.api.model.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;

import java.util.Date;

/**
 * MP注入处理器
 */
@Slf4j
public class CreateAndUpdateMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = ObjectUtil.isNotNull(baseEntity.getCreateTime())
                        ? baseEntity.getCreateTime() : new Date();
                baseEntity.setCreateTime(current);
                baseEntity.setUpdateTime(current);
                Long userId = baseEntity.getCreateUser() != null
                        ? baseEntity.getCreateUser() : getLoginUsername();
                // 当前已登录 且 创建人为空 则填充
                baseEntity.setCreateUser(userId);
                // 当前已登录 且 更新人为空 则填充
                baseEntity.setUpdateUser(userId);

                // 自动设置isDeleted
                String isDeleted = baseEntity.getIsDeleted();
                if (StrUtil.isBlank(isDeleted)) {
                    baseEntity.setIsDeleted(CommonConstants.NO_DELETED);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        try {
            if (ObjectUtil.isNotNull(metaObject) && metaObject.getOriginalObject() instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) metaObject.getOriginalObject();
                Date current = new Date();
                // 更新时间填充(不管为不为空)
                baseEntity.setUpdateTime(current);
                Long userId = getLoginUsername();
                // 当前已登录 更新人填充(不管为不为空)
                if (userId != null) {
                    baseEntity.setUpdateUser(userId);
                }
            }
        } catch (Exception e) {
            throw new ServiceException("自动注入异常 => " + e.getMessage(), HttpStatus.HTTP_UNAUTHORIZED);
        }
    }

    /**
     * 获取登录用户名
     */
    private Long getLoginUsername() {
        LoginUser loginUser;
        try {
            loginUser = LoginHelper.getLoginUser();
        } catch (Exception e) {
            log.warn("自动注入警告 => 用户未登录");
            return null;
        }
        return loginUser.getUserId();
    }

}
