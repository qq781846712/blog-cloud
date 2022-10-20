package com.blank.common.dict.utils;

import com.blank.common.core.constant.CacheNames;
import com.blank.common.redis.utils.CacheUtils;

/**
 * 字典工具类
 */
public class DictUtils {
    /**
     * 设置字典缓存
     *
     * @param key       参数键
     * @param dictDatas 字典数据列表
     */
    /*public static void setDictCache(String key, List<SysDictData> dictDatas) {
        CacheUtils.put(CacheNames.SYS_DICT, key, dictDatas);
    }*/

    /**
     * 获取字典缓存
     *
     * @param key 参数键
     * @return dictDatas 字典数据列表
     */
    /*public static List<SysDictData> getDictCache(String key) {
        return CacheUtils.get(CacheNames.SYS_DICT, key);
    }*/

    /**
     * 删除指定字典缓存
     *
     * @param key 字典键
     */
    public static void removeDictCache(String key) {
        CacheUtils.evict(CacheNames.SYS_DICT, key);
    }

    /**
     * 清空字典缓存
     */
    public static void clearDictCache() {
        CacheUtils.clear(CacheNames.SYS_DICT);
    }

}
