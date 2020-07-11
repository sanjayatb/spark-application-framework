package com.stb.spark.transformation;

import org.apache.spark.sql.Dataset;

public class Extractor implements Transformer {

    @Override
    public Dataset<?> transform(Dataset<?> input) {
        return input;
    }
}
