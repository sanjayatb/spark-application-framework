package com.stb.spark.configuration;

public class StreamingQueryConfig {

    private String name;
    private SourceConfig source;
    private String transformer;
    private SinkConfig sink;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SourceConfig getSource() {
        return source;
    }

    public void setSource(SourceConfig source) {
        this.source = source;
    }

    public String getTransformer() {
        return transformer;
    }

    public void setTransformer(String transformer) {
        this.transformer = transformer;
    }

    public SinkConfig getSink() {
        return sink;
    }

    public void setSink(SinkConfig sink) {
        this.sink = sink;
    }
}
