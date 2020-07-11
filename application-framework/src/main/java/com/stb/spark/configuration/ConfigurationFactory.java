package com.stb.spark.configuration;

import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class ConfigurationFactory {

    private static ThreadLocal<Yaml> yamlThreadLocal;

    private ConfigurationFactory() {
    }

    private static Yaml getYaml() {
        if (null == yamlThreadLocal) {
            yamlThreadLocal = new ThreadLocal<>();
        }
        Yaml yaml = yamlThreadLocal.get();
        if (null == yaml) {
            yaml = new Yaml();
            yamlThreadLocal.set(yaml);
        }
        return yaml;
    }

    public static <T> T create(String fileName, Class<T> clazz) {
        URL url = ConfigurationFactory.class.getClassLoader().getResource(fileName);

        if (null == url) {
            throw new IllegalArgumentException(fileName + " not found.");
        } else {
            try (InputStream inputStream = new FileInputStream(new File(url.toURI()))) {
                return getYaml().loadAs(inputStream, clazz);
            } catch (IOException | URISyntaxException e) {
                throw new IllegalArgumentException(fileName + e.getMessage());
            }
        }
    }

    public static <T> T createByStream(String fileName, Class<T> clazz) {
        try (InputStream inputStream = ConfigurationFactory.class
                .getClassLoader().getResourceAsStream(fileName)) {
            return getYaml().loadAs(fileName, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException(fileName + " not found.");
        }
    }

}
