package com.stb.spark.configuration;

import java.util.List;

public class ApplicationConfig {

    private String appName;
    private String master;
    private List<StreamingQueryConfig> queries;

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getMaster() {
        return master;
    }

    public void setMaster(String master) {
        this.master = master;
    }

    public List<StreamingQueryConfig> getQueries() {
        return queries;
    }

    public void setQueries(List<StreamingQueryConfig> queries) {
        this.queries = queries;
    }
}
