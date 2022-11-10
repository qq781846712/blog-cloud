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
    private String roleName;
    private String roleKey;
    private String roleSort;
    private String status;
    private String remark;
    private String dataScope;

}
