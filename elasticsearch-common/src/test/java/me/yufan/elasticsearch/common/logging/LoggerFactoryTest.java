package me.yufan.elasticsearch.common.logging;

import me.yufan.elasticsearch.common.logging.commons.JakartaCommonsLoggingImpl;
import me.yufan.elasticsearch.common.logging.jdk14.Jdk14LoggingImpl;
import me.yufan.elasticsearch.common.logging.log4j.Log4jImpl;
import me.yufan.elasticsearch.common.logging.log4j2.Log4j2Impl;
import me.yufan.elasticsearch.common.logging.nologging.NoLoggingImpl;
import me.yufan.elasticsearch.common.logging.slf4j.Slf4jImpl;
import me.yufan.elasticsearch.common.logging.stdout.StdOutImpl;
import org.junit.Test;

import static org.hamcrest.Matchers.instanceOf;
import static org.junit.Assert.assertThat;

public class LoggerFactoryTest {

    @Test
    public void useCommonsLogging() {
        LoggerFactory.useCommonsLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(JakartaCommonsLoggingImpl.class));
    }

    @Test
    public void useLog4J() {
        LoggerFactory.useLog4JLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(Log4jImpl.class));
    }

    @Test
    public void useLog4J2() {
        LoggerFactory.useLog4J2Logging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(Log4j2Impl.class));
    }

    @Test
    public void useJdKLogging() {
        LoggerFactory.useJdkLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(Jdk14LoggingImpl.class));
    }

    @Test
    public void useSlf4J() {
        LoggerFactory.useSlf4jLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(Slf4jImpl.class));
    }

    @Test
    public void useStdOut() {
        LoggerFactory.useStdOutLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(StdOutImpl.class));
    }

    @Test
    public void useNoLogging() {
        LoggerFactory.useNoLogging();
        Logger log = LoggerFactory.getLog(Object.class);
        logSomething(log);
        assertThat(log, instanceOf(NoLoggingImpl.class));
    }

    private void logSomething(Logger log) {
        log.warn("Warning message.");
        log.debug("Debug message.");
        log.error("Error message.");
        log.error("Error with Exception.", new Exception("Test exception."));
    }
}
