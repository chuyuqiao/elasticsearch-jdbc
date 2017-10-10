package me.yufan.elasticsearch.common.logging.stdout;

public class StdOutImpl implements me.yufan.elasticsearch.common.logging.Logger {

    public StdOutImpl(String clazz) {
        // Do Nothing
    }

    @Override
    public boolean isDebugEnabled() {
        return true;
    }

    @Override
    public boolean isTraceEnabled() {
        return true;
    }

    @Override
    public void error(String s, Throwable e) {
        System.err.println(s); // NOSONAR Known issues
        e.printStackTrace(System.err); // NOSONAR Known issues
    }

    @Override
    public void error(String s) {
        System.err.println(s); // NOSONAR Known issues
    }

    @Override
    public void debug(String s) {
        System.out.println(s); // NOSONAR Known issues
    }

    @Override
    public void trace(String s) {
        System.out.println(s); // NOSONAR Known issues
    }

    @Override
    public void warn(String s) {
        System.out.println(s); // NOSONAR Known issues
    }
}
