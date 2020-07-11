package com.stb.spark.transformation;

import org.apache.spark.sql.Dataset;

public interface Transformer {

    Dataset<?> transform(Dataset<?> input);

}
