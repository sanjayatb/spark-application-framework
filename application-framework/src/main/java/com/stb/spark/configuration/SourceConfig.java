package com.stb.spark.configuration;

import java.util.Map;

public class SourceConfig {

    private String format;
    private Map<String,String> options;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
