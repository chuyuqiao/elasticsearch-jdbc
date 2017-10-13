package me.yufan.elasticsearch.backend;

import me.yufan.elasticsearch.parser.SQLTemplate;

import java.sql.SQLException;

/**
 * Interface for compile SQL template into JSON
 */
public interface JSONCompiler {

    /**
     * Check the SQL Template is supported to process
     *
     * @param template The sql template parsed by antlr4
     * @throws SQLException Would be thrown if the sql is not valid
     */
    boolean canCompile(SQLTemplate template) throws SQLException;

    /**
     * Compile the SQL Template into ES readable str
     *
     * @param template The sql template parsed by antlr4
     * @throws SQLException Would be thrown if some error occur
     */
    String compile(SQLTemplate template) throws SQLException;
}
