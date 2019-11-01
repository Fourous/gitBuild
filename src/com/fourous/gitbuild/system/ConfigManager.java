package com.fourous.gitbuild.system;

import com.fourous.gitbuild.system.users.UserConfig;
import com.fourous.gitbuild.util.FileUtil;
import com.fourous.gitbuild.util.JsonUtil;
import com.fourous.gitbuild.util.SystemUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

/**
 * @author fourous
 * @date: 2019/11/1
 * @description: 日志配置管理器
 */
public class ConfigManager {
    private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    private static final String SYSTEM_PATH_SEPARATOR = File.separator;

    private static final String USER_INFO_CONFIG_FILE_PATH_RELATIVE = replaceBySystemPathSeparator("conf\\users\\users.conf");

    private static UserConfig userConfig;

    /**
     * 获取权限配置
     *
     * @return
     */
    public static UserConfig getUserConfig() {
        return userConfig;
    }

    static {
        loadAllConfig();
    }

    public static void loadAllConfig() {
        loadUserConfig();
    }

    /**
     * 加载资源配置
     */
    private static void loadUserConfig() {
        String filePath = getConfigFilePath(USER_INFO_CONFIG_FILE_PATH_RELATIVE);
        String fileContent = FileUtil.getFileContent(filePath);

        userConfig = JsonUtil.toObject(fileContent, UserConfig.class);
    }

    /**
     * 路径转换
     *
     * @param str
     * @return
     */
    private static String replaceBySystemPathSeparator(String str) {
        return str.replace("\\", SYSTEM_PATH_SEPARATOR);
    }

    /**
     * 获取配置文件绝对路径
     *
     * @param relativePath
     * @return
     */
    public static String getConfigFilePath(String relativePath) {
        String classPath = SystemUtil.getWebClassPath();
        return classPath + SYSTEM_PATH_SEPARATOR + relativePath;
    }

    /**
     * 获取系统配置
     *
     * @param name
     * @return
     */
    public static String getSystemProperty(String name) {
        Properties systemProperties = new Properties();
        try {
            ClassPathResource resource = new ClassPathResource("system.properties");
            systemProperties.load(resource.getInputStream());
        } catch (IOException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
        }
        return systemProperties.getProperty(name);
    }
}
