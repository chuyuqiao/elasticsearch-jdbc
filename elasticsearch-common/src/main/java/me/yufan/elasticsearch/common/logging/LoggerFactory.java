package me.yufan.elasticsearch.common.logging;

import lombok.experimental.UtilityClass;
import me.yufan.elasticsearch.common.logging.commons.JakartaCommonsLoggingImpl;
import me.yufan.elasticsearch.common.logging.jdk14.Jdk14LoggingImpl;
import me.yufan.elasticsearch.common.logging.log4j.Log4jImpl;
import me.yufan.elasticsearch.common.logging.log4j2.Log4j2Impl;
import me.yufan.elasticsearch.common.logging.nologging.NoLoggingImpl;
import me.yufan.elasticsearch.common.logging.slf4j.Slf4jImpl;
import me.yufan.elasticsearch.common.logging.stdout.StdOutImpl;

import java.lang.reflect.Constructor;

@UtilityClass
public class LoggerFactory {

    /**
     * Marker to be used by logging implementations that support markers
     */
    public final String MARKER = "ELASTIC_SEARCH_JDBC";

    private Constructor<? extends Logger> logConstructor;

    static {
        // Tweak the log order for default logger implement
        tryImplementation(LoggerFactory::useNoLogging);
        tryImplementation(LoggerFactory::useJdkLogging);
        tryImplementation(LoggerFactory::useCommonsLogging);
        tryImplementation(LoggerFactory::useLog4JLogging);
        tryImplementation(LoggerFactory::useLog4J2Logging);
        tryImplementation(LoggerFactory::useSlf4jLogging);
    }

    public Logger getLog(Class<?> aClass) {
        return getLog(aClass.getName());
    }

    public Logger getLog(String logger) {
        try {
            return logConstructor.newInstance(logger);
        } catch (Throwable t) { // NOSONAR Known issues, but it's required to catch Throwable
            throw new LogException("Error creating logger for logger " + logger + ".  Cause: " + t, t);
        }
    }

    public synchronized void useCustomLogging(Class<? extends Logger> clazz) {
        setImplementation(clazz);
    }

    public synchronized void useSlf4jLogging() {
        setImplementation(Slf4jImpl.class);
    }

    public synchronized void useCommonsLogging() {
        setImplementation(JakartaCommonsLoggingImpl.class);
    }

    public synchronized void useLog4JLogging() {
        setImplementation(Log4jImpl.class);
    }

    public synchronized void useLog4J2Logging() {
        setImplementation(Log4j2Impl.class);
    }

    public synchronized void useJdkLogging() {
        setImplementation(Jdk14LoggingImpl.class);
    }

    public synchronized void useStdOutLogging() {
        setImplementation(StdOutImpl.class);
    }

    public synchronized void useNoLogging() {
        setImplementation(NoLoggingImpl.class);
    }

    private void tryImplementation(Runnable runnable) {
        if (logConstructor == null) {
            try {
                runnable.run();
            } catch (Throwable t) { // NOSONAR Known issues, but it's required to catch Throwable
                // ignore
            }
        }
    }

    private void setImplementation(Class<? extends Logger> implClass) {
        try {
            Constructor<? extends Logger> candidate = implClass.getConstructor(String.class);
            Logger log = candidate.newInstance(LoggerFactory.class.getName());
            if (log.isDebugEnabled()) {
                log.debug("Logging initialized using '" + implClass + "' adapter.");
            }
            logConstructor = candidate;
        } catch (Throwable t) { // NOSONAR Known issues, but it's required to catch Throwable
            throw new LogException("Error setting Log implementation.  Cause: " + t, t);
        }
    }
}
