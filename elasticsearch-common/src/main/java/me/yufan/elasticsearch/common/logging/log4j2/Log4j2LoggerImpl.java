package me.yufan.elasticsearch.common.logging.log4j2;

import me.yufan.elasticsearch.common.logging.LoggerFactory;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;

public class Log4j2LoggerImpl implements me.yufan.elasticsearch.common.logging.Logger {

    private static final Marker MARKER = MarkerManager.getMarker(LoggerFactory.MARKER);

    private final Logger log;

    public Log4j2LoggerImpl(Logger logger) {
        log = logger;
    }

    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }

    @Override
    public void error(String s, Throwable e) {
        log.error(MARKER, s, e);
    }

    @Override
    public void error(String s) {
        log.error(MARKER, s);
    }

    @Override
    public void debug(String s) {
        log.debug(MARKER, s);
    }

    @Override
    public void trace(String s) {
        log.trace(MARKER, s);
    }

    @Override
    public void warn(String s) {
        log.warn(MARKER, s);
    }

}
