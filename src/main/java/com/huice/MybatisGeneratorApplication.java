package com.huice;

import com.huice.generator.MybatisGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Collections;
import java.util.Optional;

@SpringBootApplication
public class MybatisGeneratorApplication implements ApplicationRunner {

    @Value("${targetTable}")
    private String targetTable;
    @Value("${driver}")
    private String driver;
    @Value("${url}")
    private String url;
    @Value("${dbUsername}")
    private String dbUsername;
    @Value("${password}")
    private String password;
    @Value("${author}")
    private String author;
    @Value("${projectName}")
    private String projectName;
    @Value("${baseDirPath}")
    private String baseDirPath;

    public static void main(String[] args) {
        new SpringApplicationBuilder(MybatisGeneratorApplication.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) {

        MybatisGenerator mybatisGenerator = new MybatisGenerator(buildConfig(args));

        mybatisGenerator.execute();
    }

    /**
     * Config构建方法
     * @param args cmd参数集合
     * @return {@link Config}
     * @author Li Xiaobing
     * @date 2020/12/29 9:52
     */
    private Config buildConfig(ApplicationArguments args) {
        targetTable = optionValueBuild("targetTable",targetTable,args);
        driver = optionValueBuild("driver",driver,args);
        url = optionValueBuild("url",url,args);
        dbUsername = optionValueBuild("dbUsername",dbUsername,args);
        password = optionValueBuild("password",password,args);
        author = optionValueBuild("author",author,args);
        projectName = optionValueBuild("projectName",projectName,args);
        baseDirPath = optionValueBuild("baseDirPath",baseDirPath,args);


        Config config = new Config();
        config.setTargetTable(targetTable).setDriver(driver).setUrl(url).setDbUsername(dbUsername).setPassword(password)
                .setAuthor(author).setProjectName(projectName).setBaseDirPath(baseDirPath);
        return config;
    }

    /**
     *
     * @param fieldName 待处理字段名
	 * @param ymlValue 字段从yml读取的默认值
	 * @param args cmd命令行参入集合对象
     * @return {@link String} 如果cmd命令行传入了参数则命令行优先，否则使用yml中的值
     * @author Li Xiaobing
     * @date 2020/12/29 9:49
     */
    private String optionValueBuild(String fieldName, String ymlValue, ApplicationArguments args) {
       return Optional.ofNullable(args.getOptionValues(fieldName)).orElse(Collections.singletonList(ymlValue)).get(0);
    }

}