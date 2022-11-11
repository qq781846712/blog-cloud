package com.blank.system.api.domain;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.blank.common.core.constant.UserConstants;
import com.blank.common.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 角色表 sys_role
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

    /**
     * 角色ID
     */
    @TableId(value = "role_id")
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    private String roleName;

    /**
     * 角色权限
     */
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    private String roleKey;

    /**
     * 角色排序
     */
    @NotNull(message = "显示顺序不能为空")
    private Integer roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    private String dataScope;

    /**
     * 角色状态（0正常 1停用）
     */
    private String status;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @TableField(exist = false)
    private boolean flag = false;

    public SysRole(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * 是否管理员
     */
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.roleId);
    }

}
