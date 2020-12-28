package com.huice.generator;

import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.sun.media.sound.SF2GlobalRegion;

import java.nio.file.NotLinkException;

import static com.baomidou.mybatisplus.generator.config.rules.DbColumnType.*;

/**
 * @author Li Xiaobing
 * @classname MyPostgreSqlTypeConvert
 * @date 2020/12/28 19:21
 */
public class MyPostgreSqlTypeConvert implements ITypeConvert {
    public static final MyPostgreSqlTypeConvert INSTANCE = new MyPostgreSqlTypeConvert();

    @Override
    public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
        fieldType = fieldType.toLowerCase();

        if (fieldType.contains("char") || fieldType.contains("text") || fieldType.contains("json") || fieldType.contains("enum")) {
            return STRING;
        }
        if (fieldType.contains("bigint")) {
            return LONG;
        }
        if (fieldType.contains("int")) {
            return INTEGER;
        }
        if (fieldType.contains("date") || fieldType.contains("time")) {
            return toDateType(globalConfig,fieldType);
        }
        if (fieldType.contains("bit")) {
            return BOOLEAN;
        }
        if (fieldType.contains("decimal") || fieldType.contains("numeric")) {
            return BIG_DECIMAL;
        }
        if (fieldType.contains("clob")) {
            return CLOB;
        }
        if (fieldType.contains("blob")) {
            return BYTE_ARRAY;
        }
        if (fieldType.contains("float")) {
            return FLOAT;
        }
        if (fieldType.contains("double")) {
            return DOUBLE;
        }
        if (fieldType.contains("boolean")) {
            return BOOLEAN;
        }
        return STRING;
    }

    public static IColumnType toDateType(GlobalConfig config, String type) {
        switch (config.getDateType()) {
            case SQL_PACK:
                switch (type) {
                    case "date":
                        return DbColumnType.DATE_SQL;
                    case "time":
                        return DbColumnType.TIME;
                    default:
                        return DbColumnType.TIMESTAMP;
                }
            case TIME_PACK:
                switch (type) {
                    case "date":
                        return DbColumnType.LOCAL_DATE;
                    case "time":
                        return DbColumnType.LOCAL_TIME;
                    case "offsetdatetime":
                        return MyDbColumnType.OFFSET_DATE_TIME;
                    default:
                        return DbColumnType.LOCAL_DATE_TIME;
                }
            default:
                return DbColumnType.DATE;
        }
    }
}
