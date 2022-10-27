package com.blank.system.api.domain;

import com.blank.common.jpa.entity.TreeEntity;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 菜单权限表 sys_menu
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "sys_menu")
@Table(name = "sys_menu")
@org.hibernate.annotations.Table(appliesTo = "sys_menu", comment = "菜单权限表")
public class SysMenu extends TreeEntity {

    /**
     * 菜单ID
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "menu_id", columnDefinition = "bigint(20) COMMENT '菜单ID'")
    private Long menuId;

    /**
     * 菜单名称
     */
    @NotBlank(message = "菜单名称不能为空")
    @Size(min = 0, max = 50, message = "菜单名称长度不能超过50个字符")
    @Column(name = "menu_name", nullable = false, columnDefinition = "varchar(50) COMMENT '菜单名称'")
    private String menuName;

    /**
     * 显示顺序
     */
    @NotNull(message = "显示顺序不能为空")
    @Column(name = "order_num", nullable = false, columnDefinition = "int(4) COMMENT '显示顺序'")
    private Integer orderNum;

    /**
     * 路由地址
     */
    @Size(min = 0, max = 200, message = "路由地址不能超过200个字符")
    @Column(name = "path", nullable = false, columnDefinition = "varchar(200) COMMENT '路由地址'")
    private String path;

    /**
     * 组件路径
     */
    @Size(min = 0, max = 255, message = "组件路径不能超过255个字符")
    @Column(name = "path", columnDefinition = "varchar(255) COMMENT '组件路径'")
    private String component;

    /**
     * 路由参数
     */
    @Column(name = "query_param", columnDefinition = "varchar(255) COMMENT '路由参数'")
    private String queryParam;

    /**
     * 是否为外链（0是 1否）
     */
    @Column(name = "is_frame", columnDefinition = "int(1) default 1 comment '是否为外链（0是 1否）'")
    private String isFrame;

    /**
     * 是否缓存（0缓存 1不缓存）
     */
    @Column(name = "is_cache", columnDefinition = "int(1) default 1 comment '是否缓存（0缓存 1不缓存）'")
    private String isCache;

    /**
     * 类型（M目录 C菜单 F按钮）
     */
    @NotBlank(message = "菜单类型不能为空")
    @Column(name = "menu_type", columnDefinition = "char(1) default '' comment '菜单类型（M目录 C菜单 F按钮）'")
    private String menuType;

    /**
     * 显示状态（0显示 1隐藏）
     */
    @Column(name = "visible", columnDefinition = "char(1) default 0 comment '显示状态（0显示 1隐藏）'")
    private String visible;

    /**
     * 菜单状态（0正常 1停用）
     */
    @Column(name = "status", columnDefinition = "char(1) default 0 comment '菜单状态（0正常 1停用）'")
    private String status;

    /**
     * 权限字符串
     */
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @Size(min = 0, max = 100, message = "权限标识长度不能超过100个字符")
    @Column(name = "perms", columnDefinition = "varchar(100) default null comment '权限字符串'")
    private String perms;

    /**
     * 菜单图标
     */
    @Column(name = "icon", columnDefinition = "varchar(100) default '#' comment '菜单图标'")
    private String icon;

}
