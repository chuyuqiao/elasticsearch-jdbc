package me.yufan.elasticsearch.common.logging.log4j2;

import me.yufan.elasticsearch.common.logging.LoggerFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.MarkerManager;
import org.apache.logging.log4j.spi.AbstractLogger;
import org.apache.logging.log4j.spi.ExtendedLoggerWrapper;

public class Log4j2AbstractLoggerImpl implements me.yufan.elasticsearch.common.logging.Logger {

    private static final Marker MARKER = MarkerManager.getMarker(LoggerFactory.MARKER);

    private static final String FQCN = Log4j2Impl.class.getName();

    private final ExtendedLoggerWrapper log;

    public Log4j2AbstractLoggerImpl(AbstractLogger abstractLogger) {
        log = new ExtendedLoggerWrapper(abstractLogger, abstractLogger.getName(), abstractLogger.getMessageFactory());
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
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, s, e);
    }

    @Override
    public void error(String s) {
        log.logIfEnabled(FQCN, Level.ERROR, MARKER, s);
    }

    @Override
    public void debug(String s) {
        log.logIfEnabled(FQCN, Level.DEBUG, MARKER, s);
    }

    @Override
    public void trace(String s) {
        log.logIfEnabled(FQCN, Level.TRACE, MARKER, s);
    }

    @Override
    public void warn(String s) {
        log.logIfEnabled(FQCN, Level.WARN, MARKER, s);
    }
}
