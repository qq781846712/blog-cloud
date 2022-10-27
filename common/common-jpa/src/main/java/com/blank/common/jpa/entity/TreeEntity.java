package com.blank.common.jpa.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Transient;

@Data
@EqualsAndHashCode(callSuper = true)
@MappedSuperclass
public class TreeEntity extends BaseEntity {

    /**
     * 父菜单名称
     */
    @Transient
    private String parentName;

    /**
     * 父菜单ID
     */
    @Column(name = "parent_id", columnDefinition = "bigint(20) COMMENT '菜单ID'")
    private Long parentId;

}
