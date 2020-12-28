package com.huice.generator;

import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.FileOutConfig;
import com.baomidou.mybatisplus.generator.config.TemplateConfig;
import com.baomidou.mybatisplus.generator.config.builder.ConfigBuilder;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.FileType;
import com.baomidou.mybatisplus.generator.engine.AbstractTemplateEngine;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.List;
import java.util.Map;

/**
 * @classname MyFreemarkerEnginer
 * @author Li Xiaobing
 * @date 2020/12/25 16:29
 */
public class MyFreemarkerTemplateEnginer extends FreemarkerTemplateEngine {

    @Override
    public MyFreemarkerTemplateEnginer init(ConfigBuilder configBuilder) {
        super.init(configBuilder);
        return this;
    }

    /**
     * 修改文件夹创建逻辑，不主动创建文件夹
     * @return {@link AbstractTemplateEngine}
     * @author Li Xiaobing
     * @date 2020/12/24 14:19
     */
    @Override
    public AbstractTemplateEngine mkdirs() {
        return this;
    }

    /**
     * 修改文件输出逻辑，仅通过InjectionConfig的FileOutConfig列表数据进行输出。
     * @return {@link AbstractTemplateEngine}
     * @author Li Xiaobing
     * @date 2020/12/24 14:16
     */
    public AbstractTemplateEngine batchOutput() {
        try {
            List<TableInfo> tableInfoList = getConfigBuilder().getTableInfoList();
            for (TableInfo tableInfo : tableInfoList) {
                Map<String, Object> objectMap = getObjectMap(tableInfo);
                Map<String, String> pathInfo = getConfigBuilder().getPathInfo();
                TemplateConfig template = getConfigBuilder().getTemplate();
                // 自定义内容
                InjectionConfig injectionConfig = getConfigBuilder().getInjectionConfig();
                if (null != injectionConfig) {
                    injectionConfig.initTableMap(tableInfo);
                    objectMap.put("cfg", injectionConfig.getMap());
                    List<FileOutConfig> focList = injectionConfig.getFileOutConfigList();
                    if (CollectionUtils.isNotEmpty(focList)) {
                        for (FileOutConfig foc : focList) {
                            if (isCreate(FileType.OTHER, foc.outputFile(tableInfo))) {
                                writerFile(objectMap, foc.getTemplatePath(), foc.outputFile(tableInfo));
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return this;
    }
}
