package com.blank.common.jpa.entity;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Column(name = "is_deleted", columnDefinition = "CHAR COMMENT '是否删除'")
    private char isDeleted = 'N';

    @CreatedBy
    @Column(name = "createUser", length = 64, columnDefinition = "bigint(20) COMMENT '创建人'")
    private Long createUser;

    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime;

    @LastModifiedBy
    @Column(name = "updateUser", length = 64, columnDefinition = "bigint(20) COMMENT '修改人'")
    private Long updateUser;

    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp updateTime;

    @Column(name = "remark", length = 2000, columnDefinition = "varchar(2000) COMMENT '备注'")
    private String remark;

    @Version
    @Column(name = "version", columnDefinition = "int(4) COMMENT '版本'")
    private int version;

}
