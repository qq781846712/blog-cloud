package com.blank.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.blank.common.core.constant.Constants;
import com.blank.common.core.utils.StringUtils;
import com.blank.common.mybatis.core.page.PageQuery;
import com.blank.common.mybatis.core.page.TableDataInfo;
import com.blank.system.api.domain.SysNotice;
import com.blank.system.domain.bo.NoticeBo;
import com.blank.system.domain.vo.NoticeVo;
import com.blank.system.mapper.SysNoticeMapper;
import com.blank.system.service.ISysNoticeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * 公告 服务层实现
 */
@RequiredArgsConstructor
@Service
public class SysNoticeServiceImpl implements ISysNoticeService {

    private final SysNoticeMapper baseMapper;

    @Override
    public TableDataInfo<NoticeVo> selectPageNoticeList(NoticeBo noticeBo, PageQuery pageQuery) {
        QueryWrapper<NoticeVo> wrapper = Wrappers.query();
        wrapper.eq("t.is_deleted", Constants.IS_DELETED_N)
                .like(StringUtils.isNotBlank(noticeBo.getNoticeTitle()), "t.notice_title", noticeBo.getNoticeTitle())
                .eq(StringUtils.isNotBlank(noticeBo.getNoticeType()), "t.notice_type", noticeBo.getNoticeType())
                .like(StringUtils.isNotBlank(noticeBo.getCreateUserName()), "su.user_name", noticeBo.getCreateUserName());
        return TableDataInfo.build(baseMapper.selectPageNoticeList(pageQuery.build(), wrapper));
    }

    /**
     * 查询公告信息
     *
     * @param noticeId 公告ID
     * @return 公告信息
     */
    @Override
    public SysNotice selectNoticeById(Long noticeId) {
        return baseMapper.selectById(noticeId);
    }

    /**
     * 查询公告列表
     *
     * @param notice 公告信息
     * @return 公告集合
     */
    @Override
    public List<SysNotice> selectNoticeList(SysNotice notice) {
        return baseMapper.selectList(new LambdaQueryWrapper<SysNotice>()
                .like(StringUtils.isNotBlank(notice.getNoticeTitle()), SysNotice::getNoticeTitle, notice.getNoticeTitle())
                .eq(StringUtils.isNotBlank(notice.getNoticeType()), SysNotice::getNoticeType, notice.getNoticeType()));
    }

    /**
     * 新增公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int insertNotice(SysNotice notice) {
        return baseMapper.insert(notice);
    }

    /**
     * 修改公告
     *
     * @param notice 公告信息
     * @return 结果
     */
    @Override
    public int updateNotice(SysNotice notice) {
        return baseMapper.updateById(notice);
    }

    /**
     * 删除公告对象
     *
     * @param noticeId 公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeById(Long noticeId) {
        return baseMapper.deleteById(noticeId);
    }

    /**
     * 批量删除公告信息
     *
     * @param noticeIds 需要删除的公告ID
     * @return 结果
     */
    @Override
    public int deleteNoticeByIds(Long[] noticeIds) {
        return baseMapper.deleteBatchIds(Arrays.asList(noticeIds));
    }
}
