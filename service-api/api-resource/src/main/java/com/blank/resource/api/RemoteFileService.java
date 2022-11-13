package com.blank.resource.api;

import com.blank.common.core.exception.ServiceException;
import com.blank.resource.api.domain.SysFile;

/**
 * 文件服务
 */
public interface RemoteFileService {

    /**
     * 上传文件
     *
     * @param file 文件信息
     * @return 结果
     */
    SysFile upload(String name, String originalFilename, String contentType, byte[] file) throws ServiceException;
}
