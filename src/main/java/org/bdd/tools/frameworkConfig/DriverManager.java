package org.bdd.tools.frameworkConfig;

import io.cucumber.java.Scenario;
import org.bdd.tools.enums.Browser;
import org.bdd.tools.enums.Device;
import org.bdd.tools.enums.EnvironmentType;
import org.bdd.tools.util.ymlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverManager {
    private WebDriver driver;
    private final String testPlatform;
    private final ymlReader reader = new ymlReader();
    private Device mobileDeviceType;
    private EnvironmentType environmentType;
    private Browser browser;
    private Scenario scenario;

    public DriverManager(){
        testPlatform = reader.getTestPlatform();
        environmentType = reader.getEnvironment();
        if (testPlatform.equals("Desktop")){
            browser = (Browser) reader.getBrowser();}
        else{
            mobileDeviceType = reader.getMobileDeviceType();}
    }

    public WebDriver getDriver(){
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

    private void setDesktopDriver(){
        switch (environmentType) {
            case REMOTE:
                setRemoteDriver();
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

    private void setRemoteDriver(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "142.0");
        chromeOptions.setCapability("platformName", "Linux");
        chromeOptions.setCapability("se:name", scenario.getName());
        chromeOptions.setCapability("se:sampleMetadata", "Sample metadata value");
        try {
            driver = new RemoteWebDriver(new URL("http://192.168.1.11:4444"), chromeOptions);
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
    }

    public void closeDriver() {
        if(driver!=null){
            driver.quit();
        }
        System.out.println("===  Driver Quit ===");
    }
    public void setScenario(Scenario scenario) {
        this.scenario = scenario;
    }
}
