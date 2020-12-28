package com.huice;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Li Xiaobing
 * @classname MybatisGenerator
 * @date 2020/12/25 16:28
 */
public class MybatisGenerator {

    private static final String TEMPLATE_BASE_PATH = "/templates";

    private static final String PATH1_CORE = "/dolphins-core/src/main/java/com/huice/core/system/";
    private static final String PATH1_CRM = "/dolphins-crm/src/main/java/com/huice/crm/system/";

    private static final String PATH2_CORE = "/dolphins-core/src/main/resources/mappers/";
    private static final String PATH2_CRM = "/dolphins-crm/src/main/resources/mappers/";

    private static final String PATH3 = "/dolphins-dbentity/src/main/java/com/huice/database/entity/";

    private Config config;

    public MybatisGenerator(Config config) {
        this.config = config;
    }

    public void execute() {
        AutoGenerator autoGenerator = new AutoGenerator();
        // 设置模板生成引擎
        autoGenerator.setTemplateEngine(new MyFreemarkerTemplateEnginer());
        // 全局配置
        autoGenerator.setGlobalConfig(initGlobalConfig(config));
        // 包名配置
        autoGenerator.setPackageInfo(initPackageConfig(config));
        // 数据库(源)配置
        autoGenerator.setDataSource(initDataSourceConfig(config));
        // 数据库表配置
        autoGenerator.setStrategy(initStrategyConfig(config));
        // 配置不同文件生成的模板
//        autoGenerator.setTemplate(initTemplateConfig());
        autoGenerator.setCfg(initInjectionConfig(config));

        autoGenerator.execute();
    }

    private GlobalConfig initGlobalConfig(Config config) {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setOutputDir(config.getBaseDirPath());
        globalConfig.setAuthor(config.getAuthor());
        globalConfig.setOpen(false);
        globalConfig.setSwagger2(true); //实体属性 Swagger2 注解
        globalConfig.setFileOverride(true);
        globalConfig.setActiveRecord(false);// 不需要ActiveRecord特性的请改为false
        globalConfig.setEnableCache(false);// XML 二级缓存
        globalConfig.setBaseResultMap(true);// XML ResultMap
        globalConfig.setBaseColumnList(true);// XML columList

        // 设置不同类的类名生成规则
//        globalConfig.setEntityName("%sEntity");
        globalConfig.setXmlName("%sMapper");
        globalConfig.setMapperName("%sDao");
        globalConfig.setServiceName("%sService");
        globalConfig.setServiceImplName("%sServiceImpl");
        globalConfig.setControllerName("%sController");
        return globalConfig;
    }

    private PackageConfig initPackageConfig(Config config) {
        PackageConfig packageConfig = new PackageConfig();
        // 这个地方可以理解为包名，但是也可以理解为目录文件名
        packageConfig.setModuleName(null);
        packageConfig.setParent("com.huice.");
        packageConfig.setController(config.getProjectName() + ".system.controller");
        packageConfig.setService(config.getProjectName() + ".system.service");
        packageConfig.setServiceImpl(config.getProjectName() + ".system.service.impl");
        packageConfig.setMapper(config.getProjectName() + ".system.dao");

        packageConfig.setXml("mappers");
        packageConfig.setEntity("database.entity");
        return packageConfig;
    }

    private DataSourceConfig initDataSourceConfig(Config config) {
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setDriverName(config.getDriver());
        dsc.setUrl(config.getUrl());
        dsc.setUsername(config.getUsername());
        dsc.setPassword(config.getPassword());
        return dsc;
    }

    private StrategyConfig initStrategyConfig(Config config) {
        StrategyConfig strategyConfig = new StrategyConfig();
        // 大写命名
        strategyConfig.setCapitalMode(true);
        // 表名下划线转驼峰
        strategyConfig.setNaming(NamingStrategy.underline_to_camel);
        // 表字段下划线转驼峰
        strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
        strategyConfig.setTablePrefix("s");
//        strategyConfig.setFieldPrefix("s");
        strategyConfig.setEnableSqlFilter(true);
        strategyConfig.setEntityLombokModel(true);
        strategyConfig.setChainModel(false);
        strategyConfig.setEntityBooleanColumnRemoveIsPrefix(false);
        strategyConfig.setRestControllerStyle(true);
        // controller层url地址用羊肉串格式显示还是驼峰
        strategyConfig.setControllerMappingHyphenStyle(true);

        // 正则表达式
        strategyConfig.setInclude(config.getTargetTable());

//        strategyConfig.setEntityTableFieldAnnotationEnable(true); // 实体类字段上的@TableField(value="密码");
//        strategyConfig.setSuperEntityClass("com.hc.entity.MySupser");
//        strategyConfig.setSuperEntityColumns("id","name");写于父类中的公共字段
//        strategyConfig.setSuperMapperClass("com.hc.mapper.MyMapper");
//        strategyConfig.setSuperServiceClass("com.hc.service.MyService");
//        strategyConfig.setSuperServiceImplClass("com.hc.service.imp.MyServiceImp");
//        strategyConfig.setSuperControllerClass("com.hc.controller.MyController");
        return strategyConfig;
    }

    private TemplateConfig initTemplateConfig() {
        TemplateConfig templateConfig = new TemplateConfig();
//        templateConfig.setController(TEMPLATE_BASE_PATH + "/controller.java");
//        templateConfig.setService(TEMPLATE_BASE_PATH + "/service.java");
//        templateConfig.setServiceImpl(TEMPLATE_BASE_PATH + "/serviceImpl.java");
//        templateConfig.setMapper(TEMPLATE_BASE_PATH + "/mapper.java");
//        templateConfig.setXml(TEMPLATE_BASE_PATH + "/mapper.xml");
//        templateConfig.setEntity(TEMPLATE_BASE_PATH + "/entity.java");
        return templateConfig;
    }

    // 此配置会优先PackageConfig 的相同输出地址配置
    private InjectionConfig initInjectionConfig(Config config) {
        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                LocalDateTime localDateTime = LocalDateTime.now();
                map.put("datetime", localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm")));
                map.put("needCheckRepeated", config.isNeedCheckRepeated());
                map.put("checkRepeatedField", config.getCheckRepeatedField());
                map.put("checkRepeatedColumn", config.getCheckRepeatedColumn());
                setMap(map);
            }
        };

        final String baseDirPath = config.getBaseDirPath();
        final String PATH1 = Config.PROJECT_NAME_CORE.equals(config.getProjectName()) ? PATH1_CORE : PATH1_CRM;
        final String PATH2 = Config.PROJECT_NAME_CORE.equals(config.getProjectName()) ? PATH2_CORE : PATH2_CRM;

        List<FileOutConfig> focList = new ArrayList<>();
        // 设置controller
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/controller.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH1 + "controller/" + tableInfo.getEntityName() + "Controller.java";
            }
        });

        // 设置service
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/service.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH1 + "service/" + tableInfo.getEntityName() + "Service.java";
            }
        });

        // 设置serviceImpl
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/serviceImpl.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH1 + "service/impl/" + tableInfo.getEntityName() + "ServiceImpl.java";
            }
        });

        // 设置dao
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/mapper.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH1 + "dao/" + tableInfo.getEntityName() + "Dao.java";
            }
        });

        // 设置xml文件位置
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/mapper.xml.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH2 + tableInfo.getEntityName() + "Mapper.xml";
            }
        });

        // 设置entity文件位置
        focList.add(new FileOutConfig(TEMPLATE_BASE_PATH + "/entity.java.ftl") {
            @Override
            public String outputFile(TableInfo tableInfo) {
                return baseDirPath + PATH3 + tableInfo.getEntityName() + ".java";
            }
        });

        cfg.setFileOutConfigList(focList);
        return cfg;
    }

}
