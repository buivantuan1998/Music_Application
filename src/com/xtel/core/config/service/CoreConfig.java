package com.xtel.core.config.service;

import com.tbv.utils.config.base.AbsConfigUtils;

public class CoreConfig extends AbsConfigUtils {

    public static CoreConfig instance;

    public CoreConfig(String configFile, boolean autoReload, long reload_delay_ms) {
        super(configFile, autoReload, reload_delay_ms);
    }

    public static synchronized CoreConfig createInstance(String configFile, boolean autoReload, long reload_delay_ms) {
        if (instance == null) {
            instance = new CoreConfig(configFile, autoReload, reload_delay_ms);
        }
        return instance;
    }

    public static int API_PORT = 9000;
    public static String CONTEXT_PATH = "/api";
    public static String DATE_TIME_FORMAT = "dd/MM/yyyy HH:mm:ss";
    public static String DATE_FORMAT = "dd/MM/yyyy";

    public static int TOKEN_LOG_MAX_LENGTH = 50;
    public static int RESPONSE_LOG_MAX_LENGTH = 500;

    @Override
    public void readProperties() throws Exception {
        API_PORT = xmlConfig.getInt("API_PORT", API_PORT);
        CONTEXT_PATH = xmlConfig.getString("CONTEXT_PATH", CONTEXT_PATH);
        TOKEN_LOG_MAX_LENGTH = xmlConfig.getInt("TOKEN_LOG_MAX_LENGTH", TOKEN_LOG_MAX_LENGTH);
        RESPONSE_LOG_MAX_LENGTH = xmlConfig.getInt("RESPONSE_LOG_MAX_LENGTH", RESPONSE_LOG_MAX_LENGTH);
    }

    @Override
    public void reloadProcess() throws Exception {

    }

}
