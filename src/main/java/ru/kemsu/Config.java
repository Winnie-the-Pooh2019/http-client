package ru.kemsu;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Properties;

@Getter
public class Config {
    public static Config instance;

    private final String baseUrl;

    private final Logger logger = LoggerFactory.getLogger(Config.class);

    private Config() {
        Properties props;

        try {
            props = new Properties();
            props.load(getClass().getClassLoader().getResourceAsStream("application.properties"));

            baseUrl = props.getProperty("url.base");
        } catch (IOException e) {
            logger.error("Error loading application properties", e);

            throw new RuntimeException(e);
        }
    }

    public static Config getInstance() {
        if (instance == null) {
            instance = new Config();
        }

        return instance;
    }
}
