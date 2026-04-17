package org.bdd.tools.frameworkConfig;

import org.bdd.tools.util.ymlReader;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Base {
    public static ymlReader ymlConfig;
    protected TestContext testContext;
    protected WebDriver driver;
    public WebDriverWait wait;

    public Base() {
    }
    public Base(TestContext testContext) {
        this.testContext=testContext;
        this.driver = testContext.getDriverRegistry().getDriver();
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(40));
    }
}
