package com.blank.common.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    @Id
    @GenericGenerator(name = "idGenerator", strategy = "uuid")
    @GeneratedValue(generator = "idGenerator")
    private String id;

    @Column(name = "is_deleted", columnDefinition = "CHAR COMMENT '是否删除'")
    private char isDeleted = 'N';

    @CreatedBy
    @Column(name = "createby", length = 64, columnDefinition = "varchar(64) COMMENT '创建人'")
    private String createby;

    @CreatedDate
    @Column(name = "create_time", columnDefinition = "DATETIME COMMENT '创建时间'")
    private Timestamp createTime;

    @LastModifiedBy
    @Column(name = "updateby", length = 64, columnDefinition = "varchar(64) COMMENT '修改人'")
    private String updateby;

    @LastModifiedDate
    @Column(name = "update_time", columnDefinition = "DATETIME COMMENT '修改时间'")
    private Timestamp updateTime;

    @Column(name = "remark", length = 2000, columnDefinition = "varchar(2000) COMMENT '备注'")
    private String remark;

}
