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
 * 操作日志记录表 oper_log
 */

@Data
@NoArgsConstructor
@Entity(name = "sys_oper_log")
@Table(name = "sys_oper_log",
        indexes = {
                @Index(columnList = "business_type", name = "sol_business_type_index"),
                @Index(columnList = "request_method", name = "sol_request_method_index"),
                @Index(columnList = "operator_type", name = "sol_operator_type_index"),
                @Index(columnList = "status", name = "sol_status_index"),
        })
@org.hibernate.annotations.Table(appliesTo = "sys_oper_log", comment = "操作日志记录表")
public class SysOperLog implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 日志主键
     */
    @Id
    @GenericGenerator(name = "snowFlow", strategy = "com.blank.common.jpa.generator.SnowflowID")
    @GeneratedValue(generator = "snowFlow")
    @Column(name = "oper_id", columnDefinition = "bigint(20) COMMENT '日志主键'")
    private Long operId;

    /**
     * 操作模块
     */
    @Column(name = "title", columnDefinition = "varchar(50) COMMENT '操作模块'")
    private String title;

    /**
     * 业务类型（0其它 1新增 2修改 3删除）
     */
    @Column(name = "business_type", columnDefinition = "int(2) COMMENT '业务类型（0其它 1新增 2修改 3删除）'")
    private Integer businessType;

    /**
     * 方法名称
     */
    @Column(name = "method", columnDefinition = "varchar(100) COMMENT '方法名称'")
    private String method;

    /**
     * 请求方式
     */
    @Column(name = "request_method", columnDefinition = "varchar(100) COMMENT '请求方式'")
    private String requestMethod;

    /**
     * 操作类别（0其它 1后台用户 2手机端用户）
     */
    @Column(name = "operator_type", columnDefinition = "int(1) COMMENT '操作类别（0其它 1后台用户 2手机端用户）'")
    private Integer operatorType;

    /**
     * 操作人员
     */
    @Column(name = "oper_name", columnDefinition = "varchar(50) COMMENT '操作人员'")
    private String operName;

    /**
     * 请求url
     */
    @Column(name = "oper_url", columnDefinition = "varchar(255) COMMENT '请求url'")
    private String operUrl;

    /**
     * 主机地址
     */
    @Column(name = "oper_ip", columnDefinition = "varchar(128) COMMENT '主机地址'")
    private String operIp;

    /**
     * 操作地址
     */
    @Column(name = "oper_location", columnDefinition = "varchar(255) COMMENT '操作地址'")
    private String operLocation;

    /**
     * 请求参数
     */
    @Column(name = "oper_param", columnDefinition = "varchar(2000) COMMENT '请求参数'")
    private String operParam;

    /**
     * 返回参数
     */
    @Column(name = "json_result", columnDefinition = "varchar(2000) COMMENT '返回参数'")
    private String jsonResult;

    /**
     * 操作状态（0正常 1异常）
     */
    @Column(name = "status", columnDefinition = "int(1) COMMENT '操作状态（0正常 1异常）'")
    private Integer status;

    /**
     * 错误消息
     */
    @Column(name = "error_msg", columnDefinition = "varchar(2000) COMMENT '错误消息'")
    private String errorMsg;

    /**
     * 操作时间
     */
    @Column(name = "oper_time", columnDefinition = "datetime COMMENT '操作时间'")
    private Date operTime;

    /**
     * 请求参数
     */
    @Transient
    private Map<String, Object> params = new HashMap<>();

    /**
     * 业务类型数组
     */
    @Transient
    private Integer[] businessTypes;

}
