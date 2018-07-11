package com.github.lavenderx.demo.logging;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.Context;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

public class LoggerStartupListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

    private static final String DEFAULT_APP_NAME = "spring-cloud-kotlin-demo";
    private static final String DEFAULT_LOG_LEVEL = "INFO";

    private volatile boolean started = false;

    @Override
    public void start() {
        if (started) {
            return;
        }

        String appName = System.getProperty("app.name");
        if (appName == null || appName.isEmpty()) {
            appName = DEFAULT_APP_NAME;
        }
        String logDir = System.getProperty("app.logger.dir");
        if (logDir == null || logDir.isEmpty()) {
            logDir = System.getProperty("user.home") + System.getProperty("file.separator") + appName;
        }
        String logLevel = System.getProperty("app.logger.level");
        if (logLevel == null || logLevel.isEmpty()) {
            logLevel = DEFAULT_LOG_LEVEL;
        }

        final Context context = getContext();
        context.putProperty("APP_NAME", appName);
        context.putProperty("LOG_DIR", logDir);
        context.putProperty("LOG_LEVEL", logLevel);

        this.started = true;
    }

    @Override
    public void stop() {

    }

    @Override
    public boolean isStarted() {
        return started;
    }

    @Override
    public boolean isResetResistant() {
        return true;
    }

    @Override
    public void onStart(LoggerContext loggerContext) {

    }

    @Override
    public void onReset(LoggerContext loggerContext) {

    }

    @Override
    public void onStop(LoggerContext loggerContext) {

    }

    @Override
    public void onLevelChange(Logger logger, Level level) {

    }
}
