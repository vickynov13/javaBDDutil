package org.bdd.tools.frameworkConfig;

import org.bdd.tools.enums.Browser;
import org.bdd.tools.enums.Device;
import org.bdd.tools.enums.EnvironmentType;
import org.bdd.tools.util.ymlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverManager {
    private WebDriver driver;
    private final String testPlatform;
    private final ymlReader reader = new ymlReader();
    private Device mobileDeviceType;
    private EnvironmentType environmentType;
    private Browser browser;


    public DriverManager(){
        testPlatform = reader.getTestPlatform();
        environmentType = reader.getEnvironment();
        if (testPlatform.equals("Desktop")){
            browser = (Browser) reader.getBrowser();}
        else{
            mobileDeviceType = reader.getMobileDeviceType();}
    }

    public WebDriver getDriver() {
        if (driver == null) {
            if (testPlatform.equals("Mobile")) {
//                appiumDriver = setMobileDriver();
//                this.driver = appiumDriver;
            } else {
                setDesktopDriver();
                driver.manage().window().maximize();
            }
        }
        return driver;
    }

    private void setDesktopDriver() {
        switch (environmentType) {
            case REMOTE:
                setRemoteDriver_SauceLabs();
                break;
            default:
                setLocalDriver();
                break;
        }
    }

    private void setLocalDriver() {
        System.out.println("Set Local Driver for browser: " + browser);
        switch (browser){
            case CHROME:
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--remote-allow-origins=*");
                this.driver = new ChromeDriver(chromeOptions);
        }
    }

    private void setRemoteDriver_SauceLabs() {
    }
}
