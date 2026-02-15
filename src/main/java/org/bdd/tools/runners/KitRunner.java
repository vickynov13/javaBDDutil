package org.bdd.tools.runners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.bdd.tools.frameworkConfig.Base;
import org.bdd.tools.util.ReportHelper;
import org.bdd.tools.util.ymlReader;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

@CucumberOptions(features = {"src/test/resources/"},
        glue = {"org.bdd.tools.stepDefs","test.stepDefinitions"},
        plugin = {
                "pretty","html:target/cucumber-html-reports/cucumber.html",
                "json:target/cucumber-html-reports/Main.json",
                "rerun:target/cucumber-html-reports/rerun.txt"},
        monochrome = true
)
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
