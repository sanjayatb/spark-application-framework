package com.stb.spark;

import com.stb.spark.configuration.ApplicationConfig;
import com.stb.spark.configuration.ConfigurationFactory;
import com.stb.spark.configuration.StreamingQueryConfig;
import com.stb.spark.transformation.Transformer;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeoutException;

public class SparkStreamingApplication {

    private static Logger LOGGER = LoggerFactory.getLogger(SparkStreamingApplication.class);
    private static SparkSession _sparkSession;

    public static void run(){
        ApplicationConfig applicationConfig = ConfigurationFactory.create("structured-streaming.yml",ApplicationConfig.class);
        if(null == _sparkSession){
            _sparkSession = SparkSession
                    .builder()
                    .appName(applicationConfig.getAppName())
                    .master(applicationConfig.getMaster())
                    .getOrCreate();
        }
        _sparkSession.sql("set spark.sql.streaming.schemaInference=true");
        try {
            List<StreamingQuery> queries = buildStreamQuery(_sparkSession,applicationConfig);
//            sparkSession.streams().awaitAnyTermination();
            awaitQueries(queries);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void run(SparkSession sparkSession){
        _sparkSession = sparkSession;
        run();
    }

    private static List<StreamingQuery> buildStreamQuery(SparkSession sparkSession,ApplicationConfig applicationConfig) throws TimeoutException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<StreamingQuery> streamingQueries = new ArrayList<>();
        for(StreamingQueryConfig queryConfig:applicationConfig.getQueries()){
            LOGGER.info("Creating {} stream query",queryConfig.getName());
            Dataset<?> input = sparkSession
                    .readStream()
                    .format(queryConfig.getSource().getFormat())
                    .options(queryConfig.getSource().getOptions())
                    .load();

            Transformer transformer = (Transformer) Class.forName(queryConfig.getTransformer()).newInstance();

            Dataset<?> output = transformer.transform(input);

            streamingQueries.add(output
                    .writeStream()
                    .format(queryConfig.getSink().getFormat())
                    .outputMode(queryConfig.getSink().getOutputMode())
                    .options(queryConfig.getSink().getOptions())
                    .start());
        }
        return streamingQueries;
    }

    private static void awaitQueries(List<StreamingQuery> queries){
        for (StreamingQuery streamingQuery:queries){
            try {
                streamingQuery.awaitTermination();
            } catch (StreamingQueryException e) {
                e.printStackTrace();
            }
        }
    }


}
