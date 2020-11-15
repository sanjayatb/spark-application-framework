# spark-application-framework
Configurable rapid spark application development framework

## Add a stream query configuration. 

```sh
appName: "Structured Streaming Application"
master: "local[2]"
queries:
- name: "query1"
  source:
    format: "csv"
    options:
      header : true
      sep : ";"
      path : "src/main/resources/inputs/"
  transformer : "com.stb.spark.transformation.Extractor"
  sink:
    format: "console"
    outputMode: "Append"
    options:
      truncate : false
- name: "query2"
  source:
    format: "kafka"
    options:
  transformer : "com.stb.spark.transformation.Extractor"
  sink:
    format: "console"
    outputMode: "Append"
    options:
      "truncate" : false
```

