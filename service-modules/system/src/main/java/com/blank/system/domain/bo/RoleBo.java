package com.blank.system.domain.bo;

import com.blank.common.mybatis.core.page.PageQuery;
import lombok.Data;

/**
 * 角色表业务对象 sys_role
 */

@Data
public class RoleBo extends PageQuery {

    private Long roleId;
    private String roleName;
    private String roleKey;
    private String status;

}
