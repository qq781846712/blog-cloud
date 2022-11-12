package com.blank.system.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.blank.common.core.constant.UserConstants;
import com.blank.common.mybatis.core.mapper.BaseMapperPlus;
import com.blank.system.api.domain.SysDictData;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 字典表 数据层
 */
@Repository
public interface SysDictDataMapper extends BaseMapperPlus<SysDictDataMapper, SysDictData, SysDictData> {

    default List<SysDictData> selectDictDataByType(String dictType) {
        return selectList(
                new LambdaQueryWrapper<SysDictData>()
                        .eq(SysDictData::getStatus, UserConstants.DICT_NORMAL)
                        .eq(SysDictData::getDictType, dictType)
                        .orderByAsc(SysDictData::getDictSort));
    }
}
