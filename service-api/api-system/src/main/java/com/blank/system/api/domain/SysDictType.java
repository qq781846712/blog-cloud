package com.blank.system.api.domain;

import com.blank.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 字典类型表 sys_dict_type
 */

@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "sys_dict_type")
@Table(name = "sys_dict_type")
@org.hibernate.annotations.Table(appliesTo = "sys_dict_type", comment = "字典类型表")
public class SysDictType extends BaseEntity {

    /**
     * 使用雪花id
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "dict_id", columnDefinition = "bigint(20) COMMENT '字典主键'")
    private Long dictId;

    /**
     * 字典名称
     */
    @NotBlank(message = "字典名称不能为空")
    @Size(min = 0, max = 100, message = "字典类型名称长度不能超过100个字符")
    @Column(name = "dict_name", columnDefinition = "varchar(100) COMMENT '字典名称'")
    private String dictName;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型类型长度不能超过100个字符")
    @Pattern(regexp = "^[a-z][a-z0-9_]*$", message = "字典类型必须以字母开头，且只能为（小写字母，数字，下滑线）")
    @Column(name = "dict_type", columnDefinition = "varchar(100) COMMENT '字典类型'")
    private String dictType;

    /**
     * 状态（0正常 1停用）
     */
    @Column(name = "status", columnDefinition = "CHAR(1) default '0' null COMMENT '状态（0正常 1停用）'")
    private String status;

}
