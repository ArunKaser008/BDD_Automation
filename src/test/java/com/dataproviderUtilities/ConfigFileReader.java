package com.dataproviderUtilities;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * @author Arun Kaser
 */
public class ConfigFileReader {
    /**
     * @properties is a Properties class instance
     * @propertyFilePath is path of configuration properties file
     */

    private final Properties properties;
    private Properties env_properties;
    private final String propertyFilePath = "src/test/resources/configs/config.properties";

    /**
     * @FileReder() constructor to initialise properties class
     */
    public ConfigFileReader() {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(propertyFilePath));
            properties = new Properties();
            env_properties = new Properties();
            try {
                properties.load(reader);
                if (properties.getProperty("ENV").equalsIgnoreCase("ENV1")) {
                    env_properties.load(new BufferedReader(new FileReader(properties.getProperty("ENV1_CONFIG_PATH"))));
                } else if (properties.getProperty("ENV").equalsIgnoreCase("ENV2")) {
                    env_properties.load(new BufferedReader(new FileReader(properties.getProperty("ENV2_CONFIG_PATH"))));
                } else {
                    throw new RuntimeException(properties.getProperty("ENV") + " Configuration properties not found");
                }
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
        }
    }

    /**
     * @getDriver method  is to fetch driverPath
     */

    public String getDriverPath() {
        String driverPath = properties.getProperty("driverPath");
        if (driverPath != null) return driverPath;
        else throw new RuntimeException("driverPath not specified in the Configuration.properties file.");
    }

    /**
     * @getProperties method  to get properties object set by @setProperties method
     */

    public Properties getProperties() {
        return env_properties;
    }

    public void setProperties(Properties properties) {
        this.env_properties = properties;
    }

    /**
     * @getImplicitlyWait method  to set wait
     */

    public long getImplicitlyWait() {
        String implicitlyWait = env_properties.getProperty("implicitlyWait");
        if (implicitlyWait != null) return Long.parseLong(implicitlyWait);
        else throw new RuntimeException("implicitlyWait not specified in the Configuration.properties file.");
    }

    /**
     * @getApplicationUrl method  to get url
     */

    public String getApplicationUrl() {
        String url = properties.getProperty("APP_URL");
        if (url != null) return url;
        else throw new RuntimeException("url not specified in the Configuration.properties file.");
    }

    /**
     * @getBrowserName method  to get browserName return type
     */
    public String getBrowserName() {
        String browserName = env_properties.getProperty("Browser");
        return browserName;
    }

    /**
     * @getReportConfigPath method  to return reportConfigPath return type
     */
    public String getReportConfigPath() {
        String reportConfigPath = env_properties.getProperty("reportConfigPath");
        if (reportConfigPath != null) return reportConfigPath;
        else
            throw new RuntimeException("Report Config Path not specified in the Configuration.properties file for the Key:reportConfigPath");
    }

}