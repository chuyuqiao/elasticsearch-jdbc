package me.yufan.elasticsearch.common.json;

import lombok.experimental.UtilityClass;
import me.yufan.elasticsearch.common.json.esjson.ESJSONAdapter;
import me.yufan.elasticsearch.common.json.fastjson.FastJSONAdaptor;
import me.yufan.elasticsearch.common.json.gson.GsonJSONAdapter;
import me.yufan.elasticsearch.common.json.jackson.JacksonJSONAdapter;
import me.yufan.elasticsearch.common.logging.Logger;
import me.yufan.elasticsearch.common.logging.LoggerFactory;

import java.util.function.Supplier;

@UtilityClass
public class JSONFactory {

    private final Logger log = LoggerFactory.getLog(JSONFactory.class);

    private volatile JSONAdapter jsonAdapter;

    static {
        tryImplementation(JSONFactory::useEsjson);
        tryImplementation(JSONFactory::useFastjson);
        tryImplementation(JSONFactory::useGson);
        tryImplementation(JSONFactory::useJackson);
    }

    public synchronized void useEsjson() {
        setImplementation(ESJSONAdapter::new);
    }

    public synchronized void useFastjson() {
        setImplementation(FastJSONAdaptor::new);
    }

    public synchronized void useGson() {
        setImplementation(GsonJSONAdapter::new);
    }

    public synchronized void useJackson() {
        setImplementation(JacksonJSONAdapter::new);
    }

    private void tryImplementation(Runnable runnable) {
        if (jsonAdapter == null) {
            try {
                runnable.run();
            } catch (Throwable t) { // NOSONAR Known issues, but it's required to catch Throwable
                // ignore
            }
        }
    }

    private void setImplementation(Supplier<JSONAdapter> supplier) {
        try {
            JSONAdapter adapter = supplier.get();
            if (adapter != null) {
                jsonAdapter = adapter;
                JSON.setAdapter(jsonAdapter);
                if (log.isDebugEnabled()) {
                    log.debug("JSON initialized using '" + JSONFactory.jsonAdapter.getName() + "' adapter.");
                }
            }
        } catch (Throwable t) { // NOSONAR Known issues, but it's required to catch Throwable
            // ignore
            throw new JSONException("Error setting JSON implementation.  Cause: " + t, t);
        }
    }

    public JSONAdapter getAdapter() {
        return jsonAdapter;
    }
}
