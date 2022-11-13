package com.blank.resource.dubbo;

import com.blank.common.core.exception.ServiceException;
import com.blank.common.core.utils.StringUtils;
import com.blank.common.oss.core.OssClient;
import com.blank.common.oss.entity.UploadResult;
import com.blank.common.oss.factory.OssFactory;
import com.blank.resource.api.RemoteFileService;
import com.blank.resource.api.domain.SysFile;
import com.blank.resource.domain.SysOss;
import com.blank.resource.mapper.SysOssMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 文件请求处理
 */
@Slf4j
@Service
@DubboService
public class RemoteFileServiceImpl implements RemoteFileService {

    @Autowired
    private SysOssMapper sysOssMapper;

    /**
     * 文件上传请求
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public SysFile upload(String name, String originalFilename, String contentType, byte[] file) throws ServiceException {
        try {
            String suffix = StringUtils.substring(originalFilename, originalFilename.lastIndexOf("."), originalFilename.length());
            OssClient storage = OssFactory.instance();
            UploadResult uploadResult = storage.uploadSuffix(file, suffix, contentType);
            // 保存文件信息
            SysOss oss = new SysOss();
            oss.setUrl(uploadResult.getUrl());
            oss.setFileSuffix(suffix);
            oss.setFileName(uploadResult.getFilename());
            oss.setOriginalName(originalFilename);
            oss.setService(storage.getConfigKey());
            sysOssMapper.insert(oss);
            SysFile sysFile = new SysFile();
            sysFile.setName(uploadResult.getFilename());
            sysFile.setUrl(uploadResult.getUrl());
            return sysFile;
        } catch (Exception e) {
            log.error("上传文件失败", e);
            throw new ServiceException("上传文件失败");
        }
    }

}
