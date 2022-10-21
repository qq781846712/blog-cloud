package com.blank.system.api.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统访问记录表 sys_logininfor
 */

@Data
@NoArgsConstructor
@Entity(name = "sys_logininfor")
@Table(name = "sys_logininfor",
        indexes = {
                @Index(columnList = "user_name", name = "sl_user_name_index"),
                @Index(columnList = "status", name = "sl_status_index"),
        })
@org.hibernate.annotations.Table(appliesTo = "sys_logininfor", comment = "系统访问记录表")
public class SysLogininfor implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "info_id", columnDefinition = "long COMMENT 'ID'")
    private Long infoId;

    /**
     * 用户账号
     */
    @Column(name = "user_name", columnDefinition = "varchar(100) COMMENT '用户账号'")
    private String userName;


    /**
     * 状态 0成功 1失败
     */
    @Column(name = "status", columnDefinition = "CHAR(1) default '0' null COMMENT '状态 0成功 1失败'")
    private String status;

    /**
     * 地址
     */
    @Column(name = "ipaddr", columnDefinition = "varchar(100) COMMENT '地址'")
    private String ipaddr;

    /**
     * 描述
     */
    @Column(name = "ipaddr", columnDefinition = "varchar(4000) COMMENT '地址'")
    private String msg;

    /**
     * 访问时间
     */
    @Column(name = "access_time", columnDefinition = "DATETIME COMMENT '访问时间'")
    private Date accessTime;

    /**
     * 请求参数
     */
    @Transient
    private Map<String, Object> params = new HashMap<>();

}
