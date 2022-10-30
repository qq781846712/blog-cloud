import {http} from "../utils/http";

export type UserResult = {
  code: number;
  success: boolean;
  msg: string;
  data: {
    /** 用户名 */
    username: string;
    /** 当前登陆用户的角色 */
    roles: Array<string>;
    /** `token` */
    accessToken: string;
    /** 用于调用刷新`accessToken`的接口时所需的`token` */
    refreshToken: string;
    /** `accessToken`的过期时间（格式'xxxx/xx/xx xx:xx:xx'） */
    expires: Date;
  };
};

export type RefreshTokenResult = {
  success: boolean;
  data: {
    /** `token` */
    accessToken: string;
    /** 用于调用刷新`accessToken`的接口时所需的`token` */
    refreshToken: string;
    /** `accessToken`的过期时间（格式'xxxx/xx/xx xx:xx:xx'） */
    expires: Date;
  };
};

export type CodeResult = {
  code: number;
  success: boolean;
  msg: string;
  data: {
    img: string;
    captchaEnabled: boolean;
    uuid: string;
  };
};

/** 登录 */
export const getLogin = (data?: object) => {
  return http.request<UserResult>("post", "/auth/login", {data});
};

/** 刷新token */
export const refreshTokenApi = (data?: object) => {
  return http.request<RefreshTokenResult>("post", "/auth/refreshToken", {data});
};

/** 获取验证码 */
export const getCode = () => {
  return http.request<CodeResult>("get", "/code", null);
};
