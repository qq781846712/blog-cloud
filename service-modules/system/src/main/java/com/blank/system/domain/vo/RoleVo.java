package com.blank.system.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 角色
 */
@Data
@NoArgsConstructor
public class RoleVo {

    private Long roleId;
    private String roleName;
    private String roleKey;
    private Integer roleSort;
    private String status;
    private String remark;
    private String dataScope;
}
