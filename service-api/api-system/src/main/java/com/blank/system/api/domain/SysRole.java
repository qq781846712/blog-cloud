package com.blank.system.api.domain;

import com.blank.common.core.constant.UserConstants;
import com.blank.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * 角色表 sys_role
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "sys_role")
@Table(name = "sys_role")
@org.hibernate.annotations.Table(appliesTo = "sys_role", comment = "角色表")
public class SysRole extends BaseEntity {

    /**
     * 角色ID
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "role_id", columnDefinition = "bigint(20) COMMENT '角色ID'")
    private Long roleId;

    /**
     * 角色名称
     */
    @NotBlank(message = "角色名称不能为空")
    @Size(min = 0, max = 30, message = "角色名称长度不能超过30个字符")
    @Column(name = "role_name", nullable = false, columnDefinition = "varchar(30) COMMENT '角色名称'")
    private String roleName;

    /**
     * 角色权限
     */
    @NotBlank(message = "权限字符不能为空")
    @Size(min = 0, max = 100, message = "权限字符长度不能超过100个字符")
    @Column(name = "role_key", nullable = false, columnDefinition = "varchar(100) COMMENT '角色权限'")
    private String roleKey;

    /**
     * 角色排序
     */
    @NotNull(message = "显示顺序不能为空")
    @Column(name = "role_sort", nullable = false, columnDefinition = "int(4) COMMENT '显示顺序'")
    private Integer roleSort;

    /**
     * 数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）
     */
    @Column(name = "data_scope", nullable = false, columnDefinition = "char(1) default '1' COMMENT '数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）'")
    private String dataScope;

    /**
     * 菜单树选择项是否关联显示（ 0：父子不互相关联显示 1：父子互相关联显示）
     */
    @Column(name = "menu_check_strictly", nullable = false, columnDefinition = "tinyint(1) default 1 COMMENT '数据范围（1：所有数据权限；2：自定义数据权限；3：本部门数据权限；4：本部门及以下数据权限；5：仅本人数据权限）'")
    private Boolean menuCheckStrictly;

    /**
     * 角色状态（0正常 1停用）
     */
    @Column(name = "status", nullable = false, columnDefinition = "char(1) COMMENT '角色状态（0正常 1停用）'")
    private String status;

    /**
     * 用户是否存在此角色标识 默认不存在
     */
    @Transient
    private boolean flag = false;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
            },
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<SysUser> users;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "role_menu",
            joinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "menu_id", referencedColumnName = "menu_id")
            },
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<SysMenu> menus;

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
