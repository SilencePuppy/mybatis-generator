package com.huice;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @classname Config
 * @author Li Xiaobing
 * @date 2020/12/25 16:18
 */
@Data
@Accessors(chain = true)
public class Config {
    private String driver;
    private String url;
    private String dbUsername;
    private String password;

    public static final String PROJECT_NAME_CORE="core";
    public static final String PROJECT_NAME_CRM="crm";
    /**core or crm*/
    private String projectName;
    /**oa_service基础路径*/
    private String baseDirPath;

    private String author;

    private String targetTable;

    private boolean needCheckRepeated;

    private String checkRepeatedField;

    private String checkRepeatedColumn;
}
