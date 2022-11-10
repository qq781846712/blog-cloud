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

    private String roleId;
    private String name;
    private String code;
    private String createTime;
    private String updateTime;
    private String createUser;
    private String updateUser;
    private String isDeleted;
    private String sort;
    private String status;
    private String remark;
    private String dataScope;

}
