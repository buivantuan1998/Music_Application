package com.bkav.sdl.core;

import com.bkav.sdl.core.config.server.RestfulServer;
import com.bkav.sdl.core.config.service.CoreConfig;
import com.bkav.sdl.core.config.service.InitConfig;
import com.bkav.sdl.core.sys.controller.BaseController;
import com.tbv.utils.app.AbsAppBase;
import com.tbv.utils.config.base.AbsConfigUtils;
import com.tbv.utils.db.DbModule;

public class MainApplication extends AbsAppBase {

    protected static MainApplication app = null;

    public MainApplication(String pathConf) {
        super(pathConf);
    }

    public static synchronized MainApplication getInstance(String pathConf) {

        if (app == null) {
            app = new MainApplication(pathConf);
        }
        return app;
    }

    public static void main(String[] args) {

        try {
            String pathConf = args != null && args.length > 0 ? args[0] : "";
            app = getInstance(pathConf);
            app.init("log4j.xml");
            app.start();
            app.addShutdownHook();

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            try {
                app.stop();
            } catch (Exception e) {
                logger.warn(e, e);
                System.exit(9);
            }
        }
    }

    @Override
    public void initConfig() {
        InitConfig.createInstance(pathConf + "init.xml");
        CoreConfig.createInstance(pathConf + "core.xml", true, InitConfig.CHECK_RELOAD_INTERVAL_FOR_CORE_CONFIG_MS);
    }

    public static DbModule dbModule;
    public static RestfulServer restServer;

    @Override
    public void start() throws Exception {

        try {
            dbModule = new DbModule(pathConf + "db.xml");
            dbModule.startUp();
        } catch (Exception e) {
            logger.warn(e.getMessage(), e);
            return;
        }

        String baseServicePackage = BaseController.class.getPackage().getName();
        logger.info(("base service package: " + baseServicePackage));

        restServer = new RestfulServer(baseServicePackage, CoreConfig.API_PORT, CoreConfig.CONTEXT_PATH, "/*");
        restServer.publish();
        logger.info("-----------------------------MODULE IS STARTED SUCCESS!-----------------------------");
    }

    @Override
    public void stop() throws Exception {

        logger.info(String.format("[%s] STOP RELOAD CONFIG THREAD ...", Thread.currentThread().getName()));
        AbsConfigUtils.stopAllReloadThread();

        restServer.shutdown();
    }

}