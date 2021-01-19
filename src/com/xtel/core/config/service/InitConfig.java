package com.xtel.core.config.service;

import com.tbv.utils.config.base.AbsConfigUtils;

/**
 * @author trongbv
 * @version 1.0
 * @created 20-JUL-2017 12:25:41
 * When app is starting then load file init.xml fisrt for init environment
 */
public class InitConfig extends AbsConfigUtils {

	public InitConfig(String configFile) {
		super(configFile);
	}

	public static InitConfig instance;
	public static synchronized InitConfig createInstance(String configFile) {
		if (instance == null) {
			instance = new InitConfig(configFile);
		}
		return instance;
	}
	
	public static long CHECK_RELOAD_INTERVAL_FOR_CORE_CONFIG_MS = 10000;
	public static long CHECK_RELOAD_INTERVAL_FOR_MAIL_CONFIG_MS = 10000;
	
	@Override
	public void readProperties() throws Exception {
		CHECK_RELOAD_INTERVAL_FOR_CORE_CONFIG_MS = xmlConfig.getLong("CHECK_RELOAD_CONFIG(0).INTERVAL_FOR_CORE_CONFIG_MS", CHECK_RELOAD_INTERVAL_FOR_CORE_CONFIG_MS);
		CHECK_RELOAD_INTERVAL_FOR_MAIL_CONFIG_MS = xmlConfig.getLong("CHECK_RELOAD_CONFIG(0).INTERVAL_FOR_MAIL_CONFIG_MS", CHECK_RELOAD_INTERVAL_FOR_MAIL_CONFIG_MS);
	}

	@Override
	public void reloadProcess() throws Exception {
		
	}
	
}
