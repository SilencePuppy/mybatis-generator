package com.huice.generator;

import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

/**
 * @author Li Xiaobing
 * @classname MyDbColumnType
 * @date 2020/12/28 19:16
 */
public enum MyDbColumnType implements IColumnType {

    // java8 新时间类型
    OFFSET_DATE_TIME("OffsetDateTime", "java.time.OffsetDateTime");


    MyDbColumnType(String type, String pkg) {
        this.type = type;
        this.pkg = pkg;
    }

    /**
     * 类型
     */
    private final String type;

    /**
     * 包路径
     */
    private final String pkg;

    @Override
    public String getType() {
        return type;
    }

    @Override
    public String getPkg() {
        return pkg;
    }
}
