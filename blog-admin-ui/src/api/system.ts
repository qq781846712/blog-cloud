import {http} from "../utils/http";

type Result = {
    success: boolean;
    data?: {
        /** 列表数据 */
        rows: Array<any>;
        /** 总数 */
        total?: number;
    };
};

/** 获取用户管理列表 */
export const getUserList = (data?: object) => {
    return http.request<Result>("post", "/system/user", {data});
};

/** 获取角色管理列表 */
export const getRoleList = (data?: object) => {
    return http.request<Result>("post", "/system/role/list", {data});
};
