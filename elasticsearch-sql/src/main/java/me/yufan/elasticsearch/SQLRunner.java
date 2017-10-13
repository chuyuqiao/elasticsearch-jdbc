package me.yufan.elasticsearch;

import me.yufan.elasticsearch.backend.CompilerFactory;
import me.yufan.elasticsearch.backend.JSONCompiler;
import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;
import me.yufan.elasticsearch.common.utils.StringUtils;
import me.yufan.elasticsearch.parser.ParserEngine;
import me.yufan.elasticsearch.parser.SQLTemplate;
import me.yufan.elasticsearch.parser.exception.ElasticSearchSQLException;

import java.sql.SQLException;
import java.util.List;

/**
 * The main entry point for sql procession. You could define it as a bean instance in Spring.
 * Or you can hold a static instance in your logic, it's thread safe.
 */
public class SQLRunner {

    private Logger log = LoggerFactory.getLog(SQLRunner.class);

    public String parseSql(String sql) throws SQLException {
        try {
            SQLTemplate template = ParserEngine.parse(sql);
            if (template != null) {
                List<JSONCompiler> compilers = CompilerFactory.getCompilers();
                String json = null;
                for (JSONCompiler compiler : compilers) {
                    if (compiler.canCompile(template)) {
                        json = compiler.compile(template);
                    }
                }
                if (StringUtils.isBlank(json)) {
                    log.error("No compiler is supported for this sql template");
                    if (log.isDebugEnabled()) {
                        log.debug(template.toString()); // TODO Using JSON.toJSONString instead.
                    }
                } else {
                    log.debug("SQL was parsed into json: " + json);
                    return json;
                }
            }
        } catch (ElasticSearchSQLException e) {
            throw new SQLException(e);
        }
        throw new SQLException("Unexpected sql error !! Check your sql sentence.");
    }
}
