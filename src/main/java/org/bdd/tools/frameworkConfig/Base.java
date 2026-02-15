package org.bdd.tools.frameworkConfig;

import org.bdd.tools.util.ymlReader;
import org.openqa.selenium.WebDriver;

public class Base {
    public static ymlReader ymlConfig;
    protected TestContext testContext;
    protected WebDriver driver;

    public Base() {
    }
    public Base(TestContext testContext) {
        this.testContext=testContext;
        this.driver = testContext.getDriverRegistry().getDriver();
    }
}
