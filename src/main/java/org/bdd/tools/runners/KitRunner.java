package org.bdd.tools.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.bdd.tools.frameworkConfig.Base;
import org.bdd.tools.util.ReportHelper;
import org.bdd.tools.util.ymlReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

public class KitRunner extends AbstractTestNGCucumberTests {

    @BeforeSuite
    public void initializeServer(){
        System.out.println("Before Suite extended");
        Base.ymlConfig = new ymlReader();
    }

    @AfterSuite(alwaysRun = true)
    public void stopServer(){
        System.out.println("After Suite extended");
        ReportHelper.generateCucumberReport();
    }
}
