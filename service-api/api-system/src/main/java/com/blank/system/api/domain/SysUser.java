package com.blank.system.api.domain;

import com.blank.common.core.annotation.Sensitive;
import com.blank.common.core.constant.UserConstants;
import com.blank.common.core.enums.SensitiveStrategy;
import com.blank.common.core.xss.Xss;
import com.blank.common.jpa.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

/**
 * 用户对象 sys_user
 */

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity(name = "sys_user")
@Table(name = "sys_user",
        indexes = {
                @Index(columnList = "user_name", name = "su_user_name_index"),
                @Index(columnList = "nick_name", name = "su_nick_name_index"),
                @Index(columnList = "status", name = "su_status_index"),
                @Index(columnList = "is_deleted", name = "su_is_deleted_index"),
        })
@org.hibernate.annotations.Table(appliesTo = "sys_user", comment = "用户对象")
public class SysUser extends BaseEntity {

    /**
     * 用户ID
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "user_id", columnDefinition = "bigint(20) COMMENT '用户ID'")
    private Long userId;

    /**
     * 用户账号
     */
    @Xss(message = "用户账号不能包含脚本字符")
    @NotBlank(message = "用户账号不能为空")
    @Size(min = 0, max = 30, message = "用户账号长度不能超过30个字符")
    @Column(name = "user_name", nullable = false, columnDefinition = "varchar(30) COMMENT '用户账号'")
    private String userName;

    /**
     * 用户昵称
     */
    @Xss(message = "用户昵称不能包含脚本字符")
    @Size(min = 0, max = 30, message = "用户昵称长度不能超过30个字符")
    @Column(name = "nick_name", nullable = false, columnDefinition = "varchar(30) COMMENT '用户昵称'")
    private String nickName;

    /**
     * 用户类型（sys_user系统用户）
     */
    @Column(name = "user_type", columnDefinition = "varchar(10) default 'sys_user' COMMENT '用户类型（sys_user系统用户）'")
    private String userType;

    /**
     * 用户邮箱
     */
    @Sensitive(strategy = SensitiveStrategy.EMAIL)
    @Email(message = "邮箱格式不正确")
    @Size(min = 0, max = 50, message = "邮箱长度不能超过50个字符")
    @Column(name = "email", columnDefinition = "varchar(50) COMMENT '用户邮箱'")
    private String email;

    /**
     * 用户性别
     */
    @Column(name = "sex", columnDefinition = "char(1) default '0' COMMENT '用户性别'")
    private String sex;

    /**
     * 用户头像
     */
    @Column(name = "avatar", columnDefinition = "varchar(100) COMMENT '头像地址'")
    private String avatar;

    /**
     * 密码
     */
    @Column(name = "password", nullable = false, columnDefinition = "varchar(100) COMMENT '密码'")
    private String password;

    @JsonIgnore
    @JsonProperty
    public String getPassword() {
        return password;
    }

    /**
     * 帐号状态（0正常 1停用）
     */
    @Column(name = "status", columnDefinition = "char(1) default '0' COMMENT '帐号状态（0正常 1停用）'")
    private String status;

    /**
     * 最后登录IP
     */
    @Column(name = "login_ip", columnDefinition = "varchar(128) COMMENT '最后登录IP'")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Column(name = "login_date", columnDefinition = "datetime COMMENT '最后登录时间'")
    private Date loginDate;

    /**
     * 角色对象
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "user_role",
            joinColumns = {
                    @JoinColumn(name = "user_id", referencedColumnName = "user_id"),
            },
            inverseJoinColumns = {
                    @JoinColumn(name = "role_id", referencedColumnName = "role_id")
            },
            foreignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT),
            inverseForeignKey = @ForeignKey(value = ConstraintMode.NO_CONSTRAINT)
    )
    private List<SysRole> roles;

    public SysUser(Long userId) {
        this.userId = userId;
    }

    /**
     * 是否管理员
     */
    public boolean isAdmin() {
        return UserConstants.ADMIN_ID.equals(this.userId);
    }

}
