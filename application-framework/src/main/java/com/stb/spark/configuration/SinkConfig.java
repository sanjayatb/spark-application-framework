package com.stb.spark.configuration;

import org.apache.spark.sql.streaming.OutputMode;

import java.util.Map;

public class SinkConfig {

    private String format;
    private String outputMode;
    private Map<String,String> options;

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getOutputMode() {
        return outputMode;
    }

    public OutputMode getOutputModeEx() {
        OutputMode _outputMode = null;
        if("Complete".equalsIgnoreCase(outputMode)){
            _outputMode = OutputMode.Complete();
        } else if("Update".equalsIgnoreCase(outputMode)){
            _outputMode = OutputMode.Update();
        } else if("Append".equalsIgnoreCase(outputMode)){
            _outputMode = OutputMode.Append();
        }
        return _outputMode;
    }

    public void setOutputMode(String outputMode) {
        this.outputMode = outputMode;
    }

    public Map<String, String> getOptions() {
        return options;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }
}
