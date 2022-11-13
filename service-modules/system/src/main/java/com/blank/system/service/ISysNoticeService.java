package com.blank.system.service;

import com.blank.common.mybatis.core.page.PageQuery;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.system.api.domain.SysNotice;
import com.blank.system.domain.bo.NoticeBo;
import com.blank.system.domain.vo.NoticeVo;

import java.util.List;

/**
 * 公告 服务层
 */
public interface ISysNoticeService {
    TableDataInfo<NoticeVo> selectPageNoticeList(NoticeBo noticeBo, PageQuery pageQuery);

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    SysNotice selectNoticeById(Long noticeId);

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    List<SysNotice> selectNoticeList(SysNotice notice);

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int insertNotice(SysNotice notice);

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    int updateNotice(SysNotice notice);

    /**
     * 删除公告信息
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    int deleteNoticeById(Long noticeId);

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    int deleteNoticeByIds(Long[] noticeIds);
}
