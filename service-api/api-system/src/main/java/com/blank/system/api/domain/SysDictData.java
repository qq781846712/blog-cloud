package com.blank.system.api.domain;

import com.blank.common.core.constant.UserConstants;
import com.blank.common.jpa.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * 字典数据表 sys_dict_data
 */
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Entity(name = "sys_dict_data")
@Table(name = "sys_dict_data",
        indexes = {
                @Index(columnList = "dict_sort", name = "sdd_dict_sort_index"),
                @Index(columnList = "dict_type", name = "sdd_dict_type_index"),
                @Index(columnList = "dict_label", name = "sdd_dict_label_index"),
        })
@org.hibernate.annotations.Table(appliesTo = "sys_dict_data", comment = "字典数据表")
public class SysDictData extends BaseEntity {

    /**
     * 使用雪花id
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "dict_code", columnDefinition = "long COMMENT '字典编码'")
    private Long dictCode;

    /**
     * 字典排序
     */
    @Column(name = "dict_sort", columnDefinition = "int(4) COMMENT '字典排序'")
    private Integer dictSort;

    /**
     * 字典标签
     */
    @NotBlank(message = "字典标签不能为空")
    @Size(min = 0, max = 100, message = "字典标签长度不能超过100个字符")
    @Column(name = "dict_label", columnDefinition = "varchar(100) COMMENT '字典标签'")
    private String dictLabel;


    /**
     * 字典键值
     */
    @NotBlank(message = "字典键值不能为空")
    @Size(min = 0, max = 100, message = "字典键值长度不能超过100个字符")
    @Column(name = "dict_value", nullable = false, length = 100, columnDefinition = "varchar(100) COMMENT '字典键值'")
    private String dictValue;

    /**
     * 字典类型
     */
    @NotBlank(message = "字典类型不能为空")
    @Size(min = 0, max = 100, message = "字典类型长度不能超过100个字符")
    @Column(name = "dict_type", nullable = false, length = 100, columnDefinition = "varchar(100) COMMENT '字典类型'")
    private String dictType;

    /**
     * 是否默认（Y是 N否）
     */
    @Column(name = "is_default", columnDefinition = "CHAR(1) default 'Y' null COMMENT '是否默认（Y是 N否）'")
    private String isDefault;

    /**
     * 状态（0正常 1停用）
     */
    @Column(name = "status", columnDefinition = "CHAR(1) default '0' null COMMENT '状态（0正常 1停用）'")
    private String status;

    public boolean getDefault() {
        return UserConstants.YES.equals(this.isDefault);
    }

}
