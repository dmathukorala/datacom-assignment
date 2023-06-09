package com.datacom.api.base;

import com.datacom.api.config.ConfigParams;
import com.datacom.api.config.ConfigProperties;
import com.datacom.api.config.ConfigUtils;
import com.datacom.api.steps.UsersSteps;
import org.json.simple.parser.JSONParser;
import org.junit.jupiter.api.BeforeAll;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestBase {

    protected static String environment;
    protected static String environmentConfigPath;
    private static String templateBodyPath;
    private static String testDataPath;
    private static JSONParser parser;

    protected static DateTimeFormatter dtf;
    protected static LocalDateTime now;

    protected static UsersSteps userSteps;

    @BeforeAll
    public static void startUp() {
        parser = new JSONParser();
        ConfigUtils.setConfigFilePath(ConfigParams.CONFIG_PATH);
        environment = System.getProperty(ConfigParams.ENVIRONMENT);
        ConfigProperties.setEnvironment(environment.toUpperCase());
        templateBodyPath = ConfigParams.TEMPLATE_BODY_PATH;
        testDataPath = ConfigUtils.getProperty(ConfigParams.TEMPLATE_DATA_PATH);
        environmentConfigPath = ConfigParams.ENV_CONFIG_PATH.replace("{environment}", environment.toUpperCase());
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        now = LocalDateTime.now();
        userSteps = new UsersSteps();
    }
}
