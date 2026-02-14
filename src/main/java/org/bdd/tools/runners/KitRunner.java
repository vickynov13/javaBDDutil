package org.bdd.tools.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class KitRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void initializeServer(){
        System.out.println("Before Suite extended");
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer(){
        System.out.println("After Suite extended");
    }
}
