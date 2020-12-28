package com.huice;

import com.huice.generator.MybatisGenerator;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.Banner;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.List;

@SpringBootApplication
public class MybatisGeneratorApplication implements ApplicationRunner {

    public static void main(String[] args) {
        new SpringApplicationBuilder(MybatisGeneratorApplication.class)
                .web(WebApplicationType.NONE) // .REACTIVE, .SERVLET
                .bannerMode(Banner.Mode.OFF)
                .run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        Config config = new Config();
        // 数据库
        config.setDriver(args.getOptionValues("driver").get(0));
        config.setUrl(args.getOptionValues("url").get(0));
        config.setUsername(args.getOptionValues("username").get(0));
        config.setPassword(args.getOptionValues("password").get(0));
        config.setTargetTable(args.getOptionValues("targetTable").get(0));

        config.setAuthor(args.getOptionValues("author").get(0));
        //项目名
        String projectName = args.getOptionValues("projectName").get(0);
        if (!Config.PROJECT_NAME_CORE.equals(projectName) && !Config.PROJECT_NAME_CRM.equals(projectName)) {
            System.out.println("项目名称不对：" + projectName);
            return;
        }
        config.setProjectName(projectName);
        config.setBaseDirPath(args.getOptionValues("baseDirPath").get(0));

        List<String> targetTable = args.getOptionValues("targetTable");
        if (targetTable == null || targetTable.size() == 0) {
            System.out.println("请输入目标表名");
            return;
        }
        config.setTargetTable(targetTable.get(0));


        List<String> checkRepeatedColumn = args.getOptionValues("checkRepeatedColumn");
        config.setNeedCheckRepeated(checkRepeatedColumn != null && checkRepeatedColumn.get(0) != null && !checkRepeatedColumn.get(0).equals(""));
        if (config.isNeedCheckRepeated()) {
            config.setCheckRepeatedColumn(checkRepeatedColumn.get(0));
            config.setCheckRepeatedField(convertUnderlineToCamel(config.getCheckRepeatedColumn()));
        }

        MybatisGenerator mybatisGenerator = new MybatisGenerator(config);

        mybatisGenerator.execute();
    }

    private String convertUnderlineToCamel(String column) {
        String[] ss = column.split("_");
        StringBuilder sb = new StringBuilder();
        sb.append(ss[0]);
        for (int i = 1; i < ss.length; i++) {
            String begin = ss[i].substring(0, 1).toUpperCase();
            String end = ss[i].substring(1);
            sb.append(begin).append(end);
        }
        return sb.toString();
    }

}