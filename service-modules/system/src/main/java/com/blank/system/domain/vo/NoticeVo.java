package com.blank.system.domain.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 通知通告
 */
@Data
@NoArgsConstructor
public class NoticeVo {

    /**
     * 公告ID
     */
    private Long noticeId;

    /**
     * 公告标题
     */
    private String noticeTitle;

    /**
     * 公告类型（1通知 2公告）
     */
    private String noticeType;

    /**
     * 公告内容
     */
    private String noticeContent;

    /**
     * 公告状态（0正常 1关闭）
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人名字
     */
    private String createUserName;

    /**
     * 备注
     */
    private String remark;

}
