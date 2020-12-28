package com.huice.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.TypeConverts;

/**
 * @author 李晓冰
 * @date 2020年12月28日
 */
public class MyDataSourceConfig extends DataSourceConfig {
    private ITypeConvert typeConvert;

    /**
     * 重写父类方法,替换为
     * @return
     */
    @Override
    public ITypeConvert getTypeConvert() {
        if (null == typeConvert) {
            DbType dbType = getDbType();
            if (DbType.POSTGRE_SQL.equals(dbType)) {
                typeConvert = MyPostgreSqlTypeConvert.INSTANCE;
            } else {
                typeConvert = TypeConverts.getTypeConvert(dbType);
                if (typeConvert == null) {
                    typeConvert = MySqlTypeConvert.INSTANCE;
                }
            }
        }
        return typeConvert;
    }
}
