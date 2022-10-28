package com.blank.system.dubbo;

import com.blank.system.api.RemoteDictService;
import com.blank.system.service.ISysDictTypeService;
import lombok.RequiredArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Service;

/**
 * 操作日志记录
 */
@RequiredArgsConstructor
@Service
@DubboService
public class RemoteDictServiceImpl implements RemoteDictService {

    private final ISysDictTypeService sysDictTypeService;


    @Override
    public String getDictLabel(String dictType, String dictValue, String separator) {
        return "";
    }

    @Override
    public String getDictValue(String dictType, String dictLabel, String separator) {
        return "";
    }
}
