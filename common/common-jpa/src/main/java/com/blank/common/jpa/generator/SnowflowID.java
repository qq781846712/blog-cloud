package com.blank.common.jpa.generator;

import cn.hutool.core.util.IdUtil;
import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

/**
 * 雪花ID生成
 */
public class SnowflowID implements IdentifierGenerator {

    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Long id = IdUtil.getSnowflake(0, 0).nextId();
        return id;
    }
}
