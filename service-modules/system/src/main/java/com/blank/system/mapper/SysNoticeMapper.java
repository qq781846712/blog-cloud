package com.blank.system.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysNotice;
import com.blank.system.domain.vo.NoticeVo;
import org.apache.ibatis.annotations.Param;

/**
 * 通知公告表 数据层
 */
public interface SysNoticeMapper extends BaseMapperPlus<SysNoticeMapper, SysNotice, NoticeVo> {
    Page<NoticeVo> selectPageNoticeList(@Param("page") Page<NoticeVo> page, @Param(Constants.WRAPPER) Wrapper<NoticeVo> queryWrapper);
}
