package com.benny.library.dynamicview.property;

import android.text.TextUtils;

import com.benny.library.dynamicview.view.DynamicViewBuilder;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class DynamicProperties {
    private Map<String, String> staticProperties = new HashMap<>();
    private Map<String, String> dynamicProperties = new HashMap<>();

    public void add(String key, String value) {
        if (!TextUtils.isEmpty(key) && !TextUtils.isEmpty(value)) {
            if (value.startsWith("{") && value.endsWith("}")) {
                dynamicProperties.put(key, value.substring(1, value.length() - 1));
            } else {
                staticProperties.put(key, value);
            }
        }
    }

    public String get(String key) {
        return  staticProperties.containsKey(key) ? staticProperties.get(key) : dynamicProperties.get(key);
    }

    public void set(DynamicViewBuilder builder) {
        for (Map.Entry<String, String> entry : staticProperties.entrySet()) {
            builder.setProperty(entry.getKey(), entry.getValue());
        }
    }

    public void set(DynamicViewBuilder builder, Map<String, String> data) {
        for (Map.Entry<String, String> entry : dynamicProperties.entrySet()) {
            if (data.containsKey(entry.getValue())) {
                builder.setProperty(entry.getKey(), data.get(entry.getValue()));
            }
        }
    }

    public void set(DynamicViewBuilder builder, JSONObject data) {
        for (Map.Entry<String, String> entry : dynamicProperties.entrySet()) {
            if (data.has(entry.getValue())) {
                builder.setProperty(entry.getKey(), data.optString(entry.getValue()));
            }
        }
    }
}