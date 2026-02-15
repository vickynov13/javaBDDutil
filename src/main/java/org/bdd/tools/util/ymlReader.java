package org.bdd.tools.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.bdd.tools.enums.Browser;
import org.bdd.tools.enums.Device;
import org.bdd.tools.enums.EnvironmentType;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.net.URISyntaxException;

public class ymlReader {
    public static LinkedHashMap<String,Object> envConfig = null;
    public String testPlatform, applicationUrl, browserName, deviceName;
    public ymlReader() {
        try {
//            //prevent outside instantiation
//            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//            java.net.URL resource = classLoader.getResource("ymlConfig.yml");
//            File file = new File(Objects.requireNonNull(classLoader.getResource("ymlConfig.yml")).getFile());
//            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
//            envConfig = (LinkedHashMap<String, Object>) objectMapper.readValue(file, Object.class);
//            System.out.println("Loading file");
//        } catch (IOException e) {
//            System.out.println(e.getMessage()+" check file format for ymlConfig.yml in root");
//            throw new RuntimeException("Exiting Test");
//        } catch (NullPointerException e){
//            System.out.println(" check ymlConfig.yml file exists in root");
//            throw new RuntimeException("Exiting Test");
//        }
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        java.net.URL resource = classLoader.getResource("ymlConfig.yml");
        File file;
//        if (resource == null) {
            // Try to find the file in the working directory (project root)
            file = new File(System.getProperty("user.dir"), "ymlConfig.yml");
            if (!file.exists()) {
                // Create a sample config file so the user can fill it in
                String sample = ""
                        +"testPlatform: \"Desktop\" # Valid values : Desktop , Mobile\n"
                        +"browser: \"CHROME\" # FIREFOX,CHROME\n"
                        +"environment: \"local\" #Valid values : local , remote\n"
                        +"mobileOS: \"android\" #Valid values : ios , android\n"
                        +"applicationUrl: \"\"\n"
                        + "driverpath:\n"
                        + "  chrome: \"path/to/chromedriver\"\n";
                try (java.io.FileWriter fw = new java.io.FileWriter(file)) {
                    fw.write(sample);
                } catch (IOException ioEx) {
                    System.out.println("Failed to create sample ymlConfig.yml: " + ioEx.getMessage());
                    throw new RuntimeException("Exiting Test", ioEx);
                }
                System.out.println("Created sample `ymlConfig.yml` at " + file.getAbsolutePath() + " - please update and restart.");
                throw new RuntimeException("Exiting Test");
            }
//            else {
//                // Found in working directory; continue and load it
//                System.out.println("`ymlConfig.yml` not on classpath but found at " + file.getAbsolutePath() + " - loading from working directory.");
//                // do not throw here; proceed to read the file below
//            }
//        }
//        else {
//            // resource may contain spaces or encoded chars; prefer URI
//            try {
//                file = new File(resource.toURI());
//            } catch (URISyntaxException use) {
//                file = new File(resource.getFile());
//            }
//        }
            ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
            envConfig = (LinkedHashMap<String, Object>) objectMapper.readValue(file, Object.class);
            System.out.println("Loading file");
        } catch (IOException e) {
            System.out.println(e.getMessage() + " check file format for ymlConfig.yml in root");
            throw new RuntimeException("Exiting Test", e);
        } catch (Exception e) {
            System.out.println(" check ymlConfig.yml file exists in root");
            throw new RuntimeException("Exiting Test", e);
        }
    }
    public String getValue(String configPath){ //driverpath.chrome.v26
        try{
            String[] path = configPath.split("\\.");
            LinkedHashMap<String,Object> val = envConfig;
            for(int i=0;i<path.length-1;i++){
                val = (LinkedHashMap<String, Object>) val.get(path[i]);
            }
            return System.getProperty(configPath)==null? (String) val.get(path[path.length-1]):System.getProperty(configPath);
        }catch (Exception e){
            return null;
        }
    }

    public String getTestPlatform() {
        return getValue("testPlatform");
    }

    public String getApplicationUrl() {
        return getValue("applicationUrl");
    }

    public Object getBrowser() {
        switch (getValue("browser").toLowerCase()) {
            case "chrome":
                return Browser.CHROME;
            case "safari":
                return Browser.SAFARI;
            case "firefox":
                return Browser.FIREFOX;
            case "edge":
                return Browser.EDGE;
            default:
                return Browser.CHROME;
        }
    }

    public Device getMobileDeviceType() {
        if (getValue("mobileOS").equalsIgnoreCase("ios")) {
            return Device.IOS;
        }
        return Device.ANDROID;
    }

    public EnvironmentType getEnvironment() {
        if(getValue("environment").equalsIgnoreCase("remote")){
            return EnvironmentType.REMOTE;
        }
        return EnvironmentType.LOCAL;
    }

//    public String getDeviceName() {
//        return deviceName;
//    }
}
