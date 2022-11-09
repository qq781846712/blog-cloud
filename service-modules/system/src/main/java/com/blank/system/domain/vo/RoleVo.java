package com.blank.system.domain.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色
 */
@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class RoleVo {

    private String id;
    private String name;
    private String code;
    private String createTime;
    private String updateTime;
    private String creator;
    private String updater;
    private String deleted;
    private String tenantId;
    private String sort;
    private String status;
    private String type;
    private String remark;
    private String dataScope;
    private String dataScopeDeptIds;

}
