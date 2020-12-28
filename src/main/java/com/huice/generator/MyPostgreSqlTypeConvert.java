package com.huice.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.*;

/**
 * @author Li Xiaobing
 * @classname MyPostgreSqlTypeConvert
 * @date 2020/12/28 19:21
 */
public class MyPostgreSqlTypeConvert implements ITypeConvert {

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        if (fieldType.contains("char") || fieldType.contains("text") || fieldType.contains("json") || fieldType.contains("enum")) {
            return STRING;
        }
        if (fieldType.contains("bigint")) {
            return LONG;
        }
        if (fieldType.contains("int"))
            return INTEGER;
    }

}
